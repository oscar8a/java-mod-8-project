package com.booklistapp.api.service;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.Genre;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public List<Book> getGenreBooks(int id){
        return genreRepository.findById(id)
                .stream().flatMap(genre -> genre.getBookList().stream())
                .collect(Collectors.toList());
    }
}
