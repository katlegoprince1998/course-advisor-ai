package com.qda.ai_course_advisor.controller;

import com.qda.ai_course_advisor.dto.AskRequest;
import com.qda.ai_course_advisor.service.GroqService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ai")
public class AIController {

    private final GroqService groqService;

    public AIController(GroqService groqService) {
        this.groqService = groqService;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody AskRequest request) {
        if (request.getQuestion() == null || request.getQuestion().isBlank()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }
        return groqService.askAI(request.getQuestion());
    }
}