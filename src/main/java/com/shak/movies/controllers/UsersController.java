package com.shak.movies.controllers;


import com.shak.movies.models.User;
import com.shak.movies.services.users.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        var savedUser =  usersService.createUser(user);
        return savedUser;
    }

    @DeleteMapping("/{userId}")
    public User deleteUser(@PathVariable String userId){
        var deletedUser = usersService.deleteUser(userId);
        return deletedUser;
    }
}
