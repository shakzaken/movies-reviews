package com.shak.movies.services.movies;

import com.shak.movies.dto.MonthlyReviewCount;
import com.shak.movies.dto.GenreRating;
import com.shak.movies.dto.TopMovieRating;
import com.shak.movies.dto.UserMovieCount;
import com.shak.movies.models.Genre;
import com.shak.movies.models.User;
import com.shak.movies.repositories.MoviesRepository;
import com.shak.movies.repositories.UsersRepository;
import org.bson.Document;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;


import java.time.Instant;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MoviesQueryServiceImpl implements MoviesQueryService{


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    private final String REVIEWS = "reviews";
    private final String MOVIE_ID = "movieId";
    private final String MOVIE_TITLE = "movieTitle";

    @Override
    public List<TopMovieRating> getTopHighestRatedMovies() {

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.unwind("reviews"),
                Aggregation.group("_id")
                        .first("title").as("movieTitle")
                        .avg("reviews.rating").as("averageRating"),
                Aggregation.project("movieTitle","averageRating").and("_id").as("movieId"),
                Aggregation.sort(Sort.Direction.DESC,"averageRating"),
                Aggregation.limit(5)
        );
        AggregationResults<TopMovieRating> results = mongoTemplate.aggregate(agg,"movies", TopMovieRating.class);

        var movies = results.getMappedResults();
        return movies;

    }

    @Override
    public List<GenreRating> getMoviesRatingByGenre() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.unwind("reviews"),
                Aggregation.group("genre")
                        .avg("reviews.rating").as("averageRating")
                        .count().as("totalReviews"),
                Aggregation.project("averageRating","totalReviews","genre").and("_id").as("genre"),
                Aggregation.sort(Sort.Direction.DESC,"averageRating"),
                Aggregation.limit(10)
        );

        AggregationResults<GenreRating>  results = mongoTemplate.aggregate(agg,"movies",GenreRating.class);

        var genreRatings = results.getMappedResults();
        return genreRatings;

    }

    @Override
    public List<UserMovieCount> getUsersThatWatchedNMovies() {
        final int N = 5;
        var agg = Aggregation.newAggregation(
                Aggregation.unwind("reviews"),
                Aggregation.group("reviews.userId")
                        .count().as("moviesCount"),
                Aggregation.match(Criteria.where("moviesCount").gte(N)),
                Aggregation.project("_id","moviesCount").and("_id").as("userId"),
                Aggregation.sort(Sort.Direction.DESC,"moviesCount")

        );

        AggregationResults<UserMovieCount>  results = mongoTemplate.aggregate(agg,"movies",UserMovieCount.class);

        var users = results.getMappedResults();

        return users;

    }

    @Override
    public List<MonthlyReviewCount> getMonthlyReviewCount() {


        Instant oneYearAgoInstant = Instant.now().minusSeconds(365L * 24 * 60 * 60);
        Date oneYearAgo = Date.from(oneYearAgoInstant);
        var agg = Aggregation.newAggregation(
                Aggregation.unwind("reviews"),
                Aggregation.match(Criteria.where("reviews.reviewDate").gte(oneYearAgo)),
                Aggregation.project("reviews.reviewDate").and(DateOperators.Month.monthOf("reviews.reviewDate")).as("month"),
                Aggregation.group("month").count().as("reviewCount"),
                Aggregation.project("_id","reviewCount").and("_id").as("month")
        );

        AggregationResults<MonthlyReviewCount> results = mongoTemplate.aggregate(agg,"movies",MonthlyReviewCount.class);


        return results.getMappedResults();
    }
}
