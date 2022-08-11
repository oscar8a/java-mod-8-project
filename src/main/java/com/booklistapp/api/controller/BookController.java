package com.booklistapp.api.controller;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@Valid @RequestBody Book incomingBookData) {
        return bookService.createBook(incomingBookData);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBook(@PathVariable int id){
        return bookService.getBookById(id);
    }
}
