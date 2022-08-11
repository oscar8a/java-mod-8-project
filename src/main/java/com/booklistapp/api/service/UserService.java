package com.booklistapp.api.service;

import com.booklistapp.api.models.User;
import com.booklistapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User incomingUserData){
        return userRepository.save(incomingUserData);
    }

    public Optional<User> getUserById(int id){
        return userRepository.findById(id);
    }

    public ResponseEntity<String> deleteUserById(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User has been deleted...", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No such user exists...", HttpStatus.BAD_REQUEST);
        }
    }
}
