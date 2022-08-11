package com.booklistapp.api.controller;

import com.booklistapp.api.models.User;
import com.booklistapp.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(User incomingUserData){
        return userService.createUser(incomingUserData);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUserById(id);
    }
}
