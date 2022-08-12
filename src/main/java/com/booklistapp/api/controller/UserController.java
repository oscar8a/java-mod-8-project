package com.booklistapp.api.controller;

import com.booklistapp.api.models.ReadingList;
import com.booklistapp.api.models.User;
import com.booklistapp.api.service.ReadingListService;
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

    @Autowired
    private ReadingListService readingListService;

    @PostMapping
    public User createUser(@Valid @RequestBody User incomingUserData){
        return userService.createUser(incomingUserData);
    }

    @PostMapping("/{id}/reading_lists")
    public ReadingList createUserReadingList(@PathVariable int id, @Valid @RequestBody ReadingList incomingData){
//        incomingData(userService.getUserById(id).orElseThrow());
        return userService.createUserReadingList(id, incomingData);
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
