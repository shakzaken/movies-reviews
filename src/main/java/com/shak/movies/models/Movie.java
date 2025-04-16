package com.shak.movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "movies")
@Getter
@Setter
@AllArgsConstructor
public class Movie {

    public Movie(){
        this.cast = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }


    @Id
    private String id;

    private String title;
    private Genre genre;
    private Date releaseDate;
    private String director;
    private List<String> cast;
    private List<Review> reviews;

    private Float averageRatings;

    public void addReview(Review review){
        String reviewId = UUID.randomUUID().toString();
        review.setId(reviewId);
        this.reviews.add(review);
    }
    public Review removeReview(String reviewId){
        Review reviewToDelete = this.reviews.stream().filter(review -> review.getId().equals(reviewId))
                .findFirst().orElseThrow();
        this.reviews.remove(reviewToDelete);
        return reviewToDelete;
    }

}
