package com.shak.movies.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review {


    private String id;

    private String userId;

    private String comment;

    private Date reviewDate;

    private Integer rating;

    //TODO set id .


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id.equals(review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
