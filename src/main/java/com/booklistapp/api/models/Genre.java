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
@Table(name = "GENRES")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Genre name may not be empty!")
    private String name;

    @ManyToMany(mappedBy = "genreSet")
    private List<Book> bookList = new ArrayList<>();
}
