package com.shak.movies.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@Document(collection = "users")
public class User {

    public User(){
        Instant nowInstance = Instant.now();
        this.registrationDate = Date.from(nowInstance);
    }

    @Id
    private String id;

    private String name;
    private String email;
    private Date registrationDate;
}
