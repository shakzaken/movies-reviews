package com.shak.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@AllArgsConstructor
public class MonthlyReviewCount {

    private Integer month;
    private Integer reviewCount;


}
