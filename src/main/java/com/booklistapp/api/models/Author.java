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
@Table(name = "AUTHORS")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Author name may not be empty!")
    private String name;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Book> bookList = new ArrayList<>();

    public void addBook(Book book) {
        bookList.add(book);
        book.setAuthor(this);
    }

    public void removeBook(Book book) {
        bookList.remove(book);
        book.setAuthor(null);
    }
}
