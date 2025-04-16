package com.shak.movies.controllers;

import com.shak.movies.models.Movie;
import com.shak.movies.models.Review;
import com.shak.movies.services.movies.MoviesService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movies")
public class MoviesController {


    private MoviesService moviesService;

    public MoviesController(MoviesService moviesService){
        this.moviesService = moviesService;
    }

    @GetMapping("/{id}")
    public Movie getMovie(@PathVariable String id){
        var movie = moviesService.getMovieById(id);
        return movie;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie){
        var createdMovie = this.moviesService.createMovie(movie);
        return createdMovie;
    }

    @PutMapping("/{movieId}")
    public Movie updateMovie(@PathVariable String movieId, @RequestBody Movie movie){
        movie.setId(movieId);
        var updatedMovie = moviesService.updateMovie(movie);
        return updatedMovie;
    }

    @DeleteMapping("/{movieId}")
    public Movie deleteMovie(@PathVariable String movieId){
        var deletedMovie = moviesService.deleteMovie(movieId);
        return deletedMovie;
    }

    @PostMapping("/{movieId}/reviews")
    public Review createReview(@PathVariable String movieId, @RequestBody Review review){
        try{
            var createdReview = moviesService.addReviewToMovie(movieId,review);
            return createdReview;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            throw exception;
        }

    }

    @DeleteMapping("/{movieId}/reviews/{reviewId}")
    public Review deleteReview(@PathVariable String movieId, @PathVariable String reviewId){
        var deletedReview = moviesService.deleteReviewFromMovie(movieId,reviewId);
        return deletedReview;
    }




}
