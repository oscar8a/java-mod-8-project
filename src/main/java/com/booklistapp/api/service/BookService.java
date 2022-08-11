package com.booklistapp.api.service;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book incomingBookData) {
        return bookRepository.save(incomingBookData);
    }

    public ResponseEntity<Book> updateBook(int id, Book incomingBookData){
        Book updateBook = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        updateBook.setTitle(incomingBookData.getTitle());
        updateBook.setPages(incomingBookData.getPages());

        bookRepository.save(updateBook);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    public List<Book> getAllBooks(){
        List<Book> bookSet = new ArrayList<>();
        bookSet = bookRepository.findAll();
        return bookSet;
    }

    public Optional<Book> getBookById(int id){
        return bookRepository.findById(id);
    }
}
