package com.example.whatsNext.service;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Map;

@Service
public class GeminiService {


    public  String apiKey;
    public String GEMINI_URL;

    public GeminiService(@Value("${api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
    }

    @Autowired
    private WebClient webClient;

    public String getGeminiResponse(String prompt) {
        // Create request body
        Map<String, Object> body = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", prompt)
                ))
            )
        );

        // Send POST request
        return webClient.post()
            .uri(GEMINI_URL)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(body)
            .retrieve()
            .bodyToMono(String.class)
            .block(); // synchronous; use `.subscribe()` if async
    }
}

