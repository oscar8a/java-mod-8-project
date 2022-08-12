package com.booklistapp.api.controller;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.Genre;
import com.booklistapp.api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping("/{id}/books")
    public List<Book> getGenreBooks(@PathVariable int id) {
        return genreService.getGenreBooks(id);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.getAllGenres();
    }
}
