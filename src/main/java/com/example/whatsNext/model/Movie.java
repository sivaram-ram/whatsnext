package com.example.whatsNext.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Data               // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @JsonProperty("MovieName")
    private String movieName;

    @JsonProperty("Year")
    private int year;

    @JsonProperty("Genre")
    private String genre;

    @JsonProperty("Language")
    private String language;
}
