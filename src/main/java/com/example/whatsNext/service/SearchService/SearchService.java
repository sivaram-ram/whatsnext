package com.example.whatsNext.service.SearchService;

import com.example.whatsNext.model.MovieFit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
public class SearchService {

    @Autowired
    private WebClient webClient;
    public  String apiKey;
    public String OMDB_URL;

    public SearchService(@Value("${OMDBapi.key}") String apiKey) {
        this.apiKey = apiKey;
        this.OMDB_URL = "http://www.omdbapi.com/?apikey=" + apiKey;
    }


    public Mono<String> getMovieSearch(String title) {
        String url = OMDB_URL + "&s=" + title;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class);
    }
    public Mono<MovieFit> getMovieDetails(String imdbID) {
        String url = OMDB_URL + "&i=" + imdbID;
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(MovieFit.class);
    }

}

    

