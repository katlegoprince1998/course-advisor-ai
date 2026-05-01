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
                    "temperature", 0.5,
                    "max_tokens", 500,
                    "messages", List.of(
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

    // 🔥 Clean structured prompt
    private String buildPrompt(String userRequest) {
        return """
                You are an AI Career Advisor for South African university students.

                Your task is to recommend suitable careers and courses based on the user's question.

                Return ONLY valid JSON in this exact format:
                {
                  "recommendations": [
                    {
                      "rank": 1,
                      "career": "",
                      "course": "",
                      "reason": "",
                      "skills_required": [],
                      "job_outlook": ""
                    }
                  ]
                }

                Rules:
                - No markdown
                - No explanations outside JSON
                - No extra text
                - Ensure JSON is valid
                - "rank" must be a number starting from 1
                - Provide at least 3 recommendations

                User Question:
                """ + userRequest;
    }

    // 🔥 Extract only AI message content
    private String extractContent(Map<String, Object> response) {
        try {
            List<?> choices = (List<?>) response.get("choices");
            Map<?, ?> firstChoice = (Map<?, ?>) choices.get(0);
            Map<?, ?> message = (Map<?, ?>) firstChoice.get("message");

            return (String) message.get("content");

        } catch (Exception e) {
            return "Error parsing AI response: " + response;
        }
    }
}