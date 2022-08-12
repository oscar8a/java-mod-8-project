package com.booklistapp.api.service;

import com.booklistapp.api.dto.BookForGetAllBooksDTO;
import com.booklistapp.api.dto.GenreBooksDTO;
import com.booklistapp.api.dto.GenreForGetAllGenresDTO;
import com.booklistapp.api.models.Book;
import com.booklistapp.api.models.Genre;
import com.booklistapp.api.repository.BookRepository;
import com.booklistapp.api.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper mapper;

    public List<GenreForGetAllGenresDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream().map(genre -> mapper.map(genre, GenreForGetAllGenresDTO.class))
                .collect(Collectors.toList());
    }

    public GenreBooksDTO getGenreBooks(int id){
        Genre genre = genreRepository.findById(id).orElseThrow();
        GenreBooksDTO genreBooksDTO = new GenreBooksDTO();

        genreBooksDTO.setName(genre.getName());
        genreBooksDTO.setBookList(genre.getBookList()
                .stream().map(book -> mapper.map(book, BookForGetAllBooksDTO.class))
                .collect(Collectors.toSet()));

        return genreBooksDTO;
    }

    public List<GenreForGetAllGenresDTO> getBookGenres(int bookId) {
//         pass in book id to get the genre
        List<GenreForGetAllGenresDTO> allGenres = getAllGenres();
//        return allGenres.stream().filter(genre -> {
//
//            List<Book> genreBookList = getGenreBooks(genre.getId());
//            genreBookList.stream().forEach(book -> {
//
//            }
//            }).collect(Collectors.toList());
        return allGenres;
    }
}
