package com.booklistapp.api.controller;

import com.booklistapp.api.models.User;
import com.booklistapp.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@Valid @RequestBody User incomingUserData){
        return userService.createUser(incomingUserData);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable int id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return userService.deleteUserById(id);
    }
}
