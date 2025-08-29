package com.example.whatsNext.service.RecMovieDetails;


import com.example.whatsNext.model.FavMovie;
import com.example.whatsNext.model.MovieFit;
import com.example.whatsNext.model.RecmndedMovies;
import com.example.whatsNext.service.SearchService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecMovieDetails {
    @Autowired
    SearchService searchService;

    public List<RecmndedMovies> getDetailsAddToList(List<String> recMovies){

        List<RecmndedMovies> myList = new ArrayList<>();
        for (String recmovie:recMovies){
            RecmndedMovies RecMovieList = searchService.getMovieDetailsByTitle(recmovie).block();
            if (RecMovieList != null) {
                myList.add(RecMovieList);
            }

        }
        return myList;
    }
}
