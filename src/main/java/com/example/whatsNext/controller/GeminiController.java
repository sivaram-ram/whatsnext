package com.example.whatsNext.controller;

import com.example.whatsNext.model.Movie;
import com.example.whatsNext.model.MovieRequest;
import com.example.whatsNext.service.ExtractMovies;
import com.example.whatsNext.service.GeminiService;
import com.example.whatsNext.service.Prompts;
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
    @Autowired
    Prompts prompts;

    @PostMapping("/movie")
    public ResponseEntity<List<String>> askGemini(@RequestBody MovieRequest movieRequest) throws JsonProcessingException {
        System.out.println(movieRequest);
        String prompt = prompts.getMovie(movieRequest.getPopularity(),movieRequest.getMovies());
        String response = geminiService.getGeminiResponse(prompt);
        List<String> responseMovies =extractMovies.changeToList(response);
        return ResponseEntity.ok(responseMovies);
    }

}
