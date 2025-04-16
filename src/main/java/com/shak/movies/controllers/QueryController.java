package com.shak.movies.controllers;


import com.shak.movies.dto.MonthlyReviewCount;
import com.shak.movies.dto.GenreRating;
import com.shak.movies.dto.TopMovieRating;
import com.shak.movies.dto.UserMovieCount;
import com.shak.movies.models.User;
import com.shak.movies.services.movies.MoviesQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/query")
public class QueryController {

    @Autowired
    private MoviesQueryService queryService;

    @GetMapping("/rated-movies")
    public List<TopMovieRating> topHighestRatedMovies(){
        return queryService.getTopHighestRatedMovies();
    }

    @GetMapping("/movies-by-genre")
    public List<GenreRating> moviesByGenre(){
        return this.queryService.getMoviesRatingByGenre();
    }

    @GetMapping("/users-watched-movies")
    public List<UserMovieCount> getUsersThatWatchedNMovies(){
        return this.queryService.getUsersThatWatchedNMovies();
    }

    @GetMapping("/monthly-review-count")
    public List<MonthlyReviewCount> monthlyReviewCount(){
        return this.queryService.getMonthlyReviewCount();
    }



}
