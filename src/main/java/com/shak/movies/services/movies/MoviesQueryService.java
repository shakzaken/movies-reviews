package com.shak.movies.services.movies;

import com.shak.movies.dto.MonthlyReviewCount;
import com.shak.movies.dto.GenreRating;
import com.shak.movies.dto.TopMovieRating;
import com.shak.movies.dto.UserMovieCount;
import com.shak.movies.models.User;

import java.util.List;

public interface MoviesQueryService {

    List<TopMovieRating> getTopHighestRatedMovies();
    List<GenreRating> getMoviesRatingByGenre();
    List<UserMovieCount> getUsersThatWatchedNMovies();
    List<MonthlyReviewCount> getMonthlyReviewCount();

}
