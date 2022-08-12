package com.booklistapp.api.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Book title may not be empty!")
    private String title;

    @Min(value = 1, message = "Book must have pages!")
    private int pages;

    private Date published;

    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    @ManyToMany
    @JoinTable(
            name = "BOOKS_READING_LISTS",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "reading_list_id")
    )
    private List<ReadingList> readingListsList = new ArrayList<>();

//    @ManyToMany
//    @JoinTable(
//            name = "BOOKS_GENRES",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "genre_id")
//    )
//    private Set<Genre> genreSet = new HashSet<>();
}
