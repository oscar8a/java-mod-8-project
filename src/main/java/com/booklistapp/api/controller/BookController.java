package com.booklistapp.api.controller;

import com.booklistapp.api.dto.BookForGetAllBooksDTO;
import com.booklistapp.api.dto.BookForGetSingleBookDTO;
import com.booklistapp.api.models.Book;
import com.booklistapp.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @GetMapping
    public List<BookForGetAllBooksDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookForGetSingleBookDTO getBook(@PathVariable int id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @Valid @RequestBody Book incomingBookUpdateData) {
        return bookService.updateBook(id, incomingBookUpdateData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        return bookService.deleteBookById(id);
    }
}
