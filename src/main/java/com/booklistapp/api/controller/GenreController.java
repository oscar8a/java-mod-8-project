package com.booklistapp.api.controller;

import com.booklistapp.api.dto.GenreBooksDTO;
import com.booklistapp.api.dto.GenreForGetAllGenresDTO;
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
    public GenreBooksDTO getGenreBooks(@PathVariable int id) {
        return genreService.getGenreBooks(id);
    }

    @GetMapping
    public List<GenreForGetAllGenresDTO> getAllGenres() {
        return genreService.getAllGenres();
    }
}
