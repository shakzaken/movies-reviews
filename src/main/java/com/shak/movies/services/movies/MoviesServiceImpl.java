package com.shak.movies.services.movies;

import com.shak.movies.models.Movie;
import com.shak.movies.models.Review;
import com.shak.movies.repositories.MoviesRepository;
import com.shak.movies.repositories.UsersRepository;
import com.shak.movies.services.users.UsersService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MoviesServiceImpl implements MoviesService{


    private MoviesRepository moviesRepository;
    private UsersService usersService;

    public MoviesServiceImpl(MoviesRepository moviesRepository, UsersService usersService){
        this.moviesRepository = moviesRepository;
        this.usersService = usersService;

    }
    @Override
    public Movie createMovie(Movie movie) {

        var savedMovie = moviesRepository.save(movie);
        return savedMovie;
    }

    @Override
    public Movie updateMovie(Movie movie) {
        Movie movieInDB = getMovieById(movie.getId());

        if(!movie.getCast().equals(movieInDB.getCast())){
            movieInDB.setCast(movie.getCast());
        }
        if(!movie.getDirector().equals(movieInDB.getDirector())){
            movieInDB.setDirector(movie.getDirector());
        }
        if(!movie.getGenre().equals(movieInDB.getGenre())){
           movieInDB.setGenre(movie.getGenre());
        }
        if(!movie.getTitle().equals(movieInDB.getTitle())){
            movieInDB.setTitle(movie.getTitle());
        }
        var updatedMovie = moviesRepository.save(movieInDB);
        return updatedMovie;
    }

    @Override
    public Movie deleteMovie(String movieId) {
        var movieFromDB = moviesRepository.findById(movieId).orElseThrow();
        moviesRepository.delete(movieFromDB);
        return movieFromDB;
    }

    @Override
    public Movie getMovieById(String movieId) {
        return moviesRepository.findById(movieId).orElseThrow();
    }

    @Override
    public Review addReviewToMovie(String movieId, Review review) {
        validateUser(review.getUserId());
        Movie movie = getMovieById(movieId);
        movie.addReview(review);
        moviesRepository.save(movie);
        return review;
    }

    @Override
    public Review deleteReviewFromMovie(String movieId, String reviewId) {
        var movie = getMovieById(movieId);
        Review deletedReview = movie.removeReview(reviewId);
        moviesRepository.save(movie);
        return deletedReview;
    }

    private void validateUser(String userId){
        try{
            var user = usersService.findUserById(userId);
        }catch (Exception ex){
            var exception = new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Id is invalid", ex);
            throw exception;
        }

    }


}
