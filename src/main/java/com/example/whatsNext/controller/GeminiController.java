package com.example.whatsNext.controller;

import com.example.whatsNext.model.Movie;
import com.example.whatsNext.service.ExtractMovies;
import com.example.whatsNext.service.GeminiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gemini")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;
    Movie movie;
    @Autowired
    ExtractMovies extractMovies;


    @PostMapping("/ask")
    public ResponseEntity<List<String>> askGemini(@RequestBody List<Movie> movies) throws JsonProcessingException {
        System.out.println(movies);
        String prompt = "Based on the following movie: " + movies +
                ", recommend 5 similar movies that belong to the same genre, appeal to fans of the original, and are lesser-known or underrated. " +
                "Respond only with a JSON array of movie titles, like this: [movie1,movie2,] — no explanation or extra text.";
        String response = geminiService.getGeminiResponse(prompt);
        List<String> responseMovies =extractMovies.changeToList(response);
        return ResponseEntity.ok(responseMovies);
    }

}
