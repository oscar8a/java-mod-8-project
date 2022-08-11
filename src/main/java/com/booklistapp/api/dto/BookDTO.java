package com.booklistapp.api.dto;

import lombok.Data;

@Data
public class BookDTO {
    private String title;
    private String authorName;
    private int pages;
}
