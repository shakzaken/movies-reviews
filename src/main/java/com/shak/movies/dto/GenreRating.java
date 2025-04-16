package com.shak.movies.dto;

import com.shak.movies.models.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreRating {

    private String genre;
    private Float averageRating;
    private Long totalReviews;
}
