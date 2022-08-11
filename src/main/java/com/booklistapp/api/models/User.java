package com.booklistapp.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "USERS_TABLE")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "User name may not be empty!")
    private String username;

    @NotBlank(message = "Password may not be blank!")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<ReadingList> readingList = new ArrayList<>();
}
