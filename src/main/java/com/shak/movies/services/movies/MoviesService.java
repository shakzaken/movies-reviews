package com.shak.movies.services.movies;

import com.shak.movies.models.Movie;
import com.shak.movies.models.Review;

public interface MoviesService {

    Movie createMovie(Movie movie);
    Movie updateMovie(Movie movie);
    Movie deleteMovie(String movieId);
    Movie getMovieById(String movieId);

    Review addReviewToMovie(String movieId, Review review);
    Review deleteReviewFromMovie(String movieId, String reviewId);
}
