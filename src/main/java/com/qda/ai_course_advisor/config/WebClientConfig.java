package com.qda.ai_course_advisor.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${GROQ_BASE_URL}")
    private String baseUrl;
    @Value("${GROQ_API_KEY}")
    private String apiKey;

    @Bean(name = "webClient")
    public WebClient AIWebClient(){
         return WebClient
                 .builder()
                 .baseUrl(baseUrl)
                 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                 .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                 .build();
    }
}
