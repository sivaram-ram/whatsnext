package com.example.whatsNext.service;

import com.example.whatsNext.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Prompts {
    public String getMovie(String popularity, List<Movie> movies){
        String prompt;
        if (popularity.equals("popular")) {
            prompt = "Based on the following movie: " + movies +
                    ", recommend 5 similar movies that belong to the same genre, appeal to fans of the original, and are popular and widely known. " +
                    "Respond only with a JSON array of movie titles, like this: [movie1,movie2,] — no explanation or extra text.";
        }
        else {
            prompt = "Based on the following movie: " + movies +
                    ", recommend 5 similar movies that belong to the same genre, appeal to fans of the original, and are lesser-known or underrated. " +
                    "Respond only with a JSON array of movie titles, like this: [movie1,movie2,] — no explanation or extra text.";
        }
        return prompt;
    }
}
