package com.example.whatsNext.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.expression.spel.ast.TypeReference;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Service
public class ExtractMovies {
    public List<String> changeToList(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

// Step 1: Parse top-level JSON
        JsonNode root = mapper.readTree(response);

// Step 2: Extract the raw string inside "parts[0].text"
        String rawText = root
                .path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

// Step 3: Remove code block formatting (```json and ```)
        String cleanedJsonArray = rawText
                .replaceAll("(?s)```json|```", "")  // removes ```json and ```
                .trim();

// Step 4: Parse the cleaned JSON array string into a List<String>
        String[] moviesArray = mapper.readValue(cleanedJsonArray, String[].class);

// Convert to List
        List<String> movieList = Arrays.asList(moviesArray);

        return movieList;
    }
}
