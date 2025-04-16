package com.shak.movies.repositories;

import com.shak.movies.models.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MoviesRepository extends MongoRepository<Movie,String> {

}
