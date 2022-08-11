package com.booklistapp.api.service;

import com.booklistapp.api.models.User;
import com.booklistapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User incomingUserData){
        return userRepository.save(incomingUserData);
    }

    public User getUserById(int id){
        return userRepository.getReferenceById(id);
    }
}
