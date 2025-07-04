package com.example.whatsNext.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.whatsNext.model.Movie;

import java.util.List;

@Data               // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {
    String popularity;
    List<Movie> movies;
}
