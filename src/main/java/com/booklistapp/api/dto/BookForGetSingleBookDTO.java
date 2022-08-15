package com.booklistapp.api.dto;

import lombok.Data;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Data
public class BookForGetSingleBookDTO {
    private String title;
    private String authorName;
//    private List<String> bookGenres;
    private int pages;
    private String datePublished;
}
