package com.example.whatsNext.controller;

import com.example.whatsNext.model.GetFullMovie;
import com.example.whatsNext.model.MovieFit;
import com.example.whatsNext.service.ExtractMovies;
import com.example.whatsNext.service.GeminiService;
import com.example.whatsNext.service.Prompts;
import com.example.whatsNext.service.SearchService.ExtractAndCallApi;
import com.example.whatsNext.service.SearchService.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/api/what")
public class GeminiController {

    @Autowired
    private GeminiService geminiService;
    @Autowired
    ExtractMovies extractMovies;
    @Autowired
    Prompts prompts;
    @Autowired
    SearchService searchService;
    @Autowired
    ExtractAndCallApi extractAndCallApi;


    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/moviesearch/{title}")
    public Mono<String> getMovie(@PathVariable String title) {
        return searchService.getMovieSearch(title); // raw JSON string sent to frontend
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/favMovie")
    public ResponseEntity<List<String>> processmovie(@RequestBody GetFullMovie getFullMovie) throws JsonProcessingException {

        List<MovieFit> fullMovie =extractAndCallApi.changeToList(getFullMovie.getMovies());
        String prompt = prompts.getMovienew(getFullMovie.getPopularity(),fullMovie);
        String response = geminiService.getGeminiResponse(prompt);
        List<String> responseMovies =extractMovies.changeToList(response);
        System.out.println(prompt);
        return ResponseEntity.ok(responseMovies);

    }


}


