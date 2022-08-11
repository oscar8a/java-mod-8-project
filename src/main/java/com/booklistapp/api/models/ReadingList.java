package com.booklistapp.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Table(name = "READINGS_LIST")
public class ReadingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Book List Name may not be empty!")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "reading_list_books",
            joinColumns = @JoinColumn(name = "reading_list_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> bookSet = new HashSet<>();
}
