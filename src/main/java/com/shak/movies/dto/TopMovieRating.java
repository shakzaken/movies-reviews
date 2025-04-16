package com.shak.movies.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopMovieRating {
    private String movieId;
    private String movieTitle;
    private Double averageRating;

}
