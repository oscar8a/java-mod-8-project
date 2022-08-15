package com.booklistapp.api.dto;

import com.booklistapp.api.models.Book;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class GenreBooksDTO {
    @NotBlank(message = "GenreBooksDTO name may not be empty!")
    @JsonProperty("genre")
    private String name;
    @JsonProperty("genre_books")
    private Set<BookForGetAllBooksDTO> bookList = new HashSet<>();
}
