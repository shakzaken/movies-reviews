package com.shak.movies.services.users;

import com.shak.movies.models.User;
import com.shak.movies.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }


    @Override
    public User createUser(User user) {
        var result = this.usersRepository.save(user);
        return result;
    }

    @Override
    public User deleteUser(String userId) {
        var userInDb = usersRepository.findById(userId).orElseThrow();
        usersRepository.delete(userInDb);
        return userInDb;
    }

    @Override
    public User findUserById(String userId) {
        var user = usersRepository.findById(userId).orElseThrow();
        return user;
    }


}
