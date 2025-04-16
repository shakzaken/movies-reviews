package com.shak.movies.repositories;

import com.shak.movies.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User,String> {
}
