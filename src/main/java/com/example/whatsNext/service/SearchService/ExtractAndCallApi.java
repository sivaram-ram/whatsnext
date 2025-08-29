
package com.example.whatsNext.service.SearchService;


import com.example.whatsNext.model.FavMovie;
import com.example.whatsNext.model.MovieFit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class ExtractAndCallApi {

    @Autowired
    SearchService searchService;

    public List<MovieFit> changeToList(List<FavMovie> favMovies){

        List<MovieFit> myList = new ArrayList<>();
        for (FavMovie favmovies:favMovies){
            MovieFit fit = searchService.getMovieDetailsById(favmovies.getImdbID()).block();
            if (fit != null) {
                myList.add(fit);
            }

        }
        return myList;
    }




}


