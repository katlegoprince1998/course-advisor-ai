package com.qda.ai_course_advisor.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

@Service
public class GroqService {

    private final WebClient webClient;

    public GroqService(@Qualifier("webClient") WebClient webClient) {
        this.webClient = webClient;
    }

    public String askAI(String userRequest) {

        try {
            Map<String, Object> requestBody = Map.of(
                    "model", "openai/gpt-oss-120b",
                    "temperature", 0.3,
                    "max_tokens", 800,
                    "top_p", 0.9,
                    "messages", List.of(
                            Map.of(
                                    "role", "system",
                                    "content", "You are a helpful AI career advisor that returns ONLY valid JSON."
                            ),
                            Map.of(
                                    "role", "user",
                                    "content", buildPrompt(userRequest)
                            )
                    )
            );

            Map<String, Object> response = webClient.post()
                    .uri("/chat/completions")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return extractContent(response);

        } catch (WebClientResponseException e) {
            System.out.println("ERROR STATUS: " + e.getStatusCode());
            System.out.println("ERROR BODY: " + e.getResponseBodyAsString());
            throw e;
        }
    }

    private String buildPrompt(String userRequest) {
        return """
            You are an AI Career Advisor for South African students.

            STRICT RULES:
            - Return ONLY valid JSON
            - Return EXACTLY 2 recommendations
            - No extra text, no markdown

            JSON format:
            {
              "recommendations": [
                {
                  "rank": 1,
                  "career": "",
                  "university": "",
                  "course": "",
                  "reason": "",
                  "skills_required": [],
                  "job_outlook": ""
                },
                {
                  "rank": 2,
                  "career": "",
                  "university": "",
                  "course": "",
                  "reason": "",
                  "skills_required": [],
                  "job_outlook": ""
                }
              ]
            }

            User Question:
            """ + userRequest;
    }

    private String extractContent(Map<String, Object> response) {
        try {
            List<?> choices = (List<?>) response.get("choices");
            Map<?, ?> firstChoice = (Map<?, ?>) choices.get(0);
            Map<?, ?> message = (Map<?, ?>) firstChoice.get("message");

            return (String) message.get("content");

        } catch (Exception e) {
            return "Error parsing response: " + response;
        }
    }
}