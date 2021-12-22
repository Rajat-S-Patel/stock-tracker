package com.example.stocktracker.Services;

import com.example.stocktracker.Models.User;
import com.example.stocktracker.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserByUserName(String username){
        return userRepository.findById(username).orElse(null);
    }
}
