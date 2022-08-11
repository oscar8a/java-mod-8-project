package com.booklistapp.api.controller;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book incomingBookData) {
        return bookService.createBook(incomingBookData);
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable int id){
        return bookService.getBookById(id);
    }
}
