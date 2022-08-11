package com.booklistapp.api.service;

import com.booklistapp.api.models.Book;
import com.booklistapp.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book createBook(Book incomingBookData) {
        return bookRepository.save(incomingBookData);
    }
}
