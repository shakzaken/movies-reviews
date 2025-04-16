package com.shak.movies.services.users;

import com.shak.movies.models.User;

public interface UsersService {

    public User createUser(User user);
    public User deleteUser(String userId);
    public User findUserById(String userId);
}
