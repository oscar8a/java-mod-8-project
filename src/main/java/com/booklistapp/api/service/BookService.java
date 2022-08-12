package com.booklistapp.api.service;

import com.booklistapp.api.dto.BookForGetAllBooksDTO;
import com.booklistapp.api.dto.BookForGetSingleBookDTO;
import com.booklistapp.api.dto.GenreForGetAllGenresDTO;
import com.booklistapp.api.models.Author;
import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.Genre;
import com.booklistapp.api.repository.AuthorRepository;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreService genreService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    public Book createBook(Book incomingBookData) {
        return bookRepository.save(incomingBookData);
    }

    public Book setBookAuthor(int id) {
        Author auth = authorRepository.findById(id).orElseThrow();
        Book book = bookRepository.findById(id).orElseThrow();
        book.setAuthor(auth);
        return bookRepository.save(book);
    }

    public ResponseEntity<Book> updateBook(int id, Book incomingBookData) {
        Book updateBook = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        updateBook.setTitle(incomingBookData.getTitle());
        updateBook.setPages(incomingBookData.getPages());

        bookRepository.save(updateBook);
        return new ResponseEntity<>(updateBook, HttpStatus.OK);
    }

    public List<BookForGetAllBooksDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream().map(book -> mapper.map(book, BookForGetAllBooksDTO.class))
                .collect(Collectors.toList());
    }

    public BookForGetSingleBookDTO getBookById(int id) {
        return bookRepository.findById(id)
                .map(book -> {
                    //DTO to return
                    BookForGetSingleBookDTO bookDTO = mapper.map(book, BookForGetSingleBookDTO.class);

                    // Format the Date according to DTO Specs
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                    String formattedPublishedDate = sdf.format(book.getPublished());

                    // Will need to access the book genres via Genre Service
                    // Get a list of Genre Entities, get the books with this genre
                    // Format the Book Genres to a list of Strings for the DTO
                    List<GenreForGetAllGenresDTO> genresBooks = genreService.getBookGenres(id);
//                    List<String> bookGenresStringList = genresBooks.stream()
//                            .map(GenreForGetAllGenresDTO::getGenre).collect(Collectors.toList());

                    // Assign newly formatted values to DTO and return
                    bookDTO.setDatePublished(formattedPublishedDate);
//                    bookDTO.setBookGenres(bookGenresStringList);
                    return bookDTO;
                })
                .orElseThrow();
    }

    public ResponseEntity<String> deleteBookById(int id) {
        if (!bookRepository.existsById(id)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        bookRepository.deleteById(id);
        return new ResponseEntity<>("Book has been deleted...", HttpStatus.OK);
    }
}
