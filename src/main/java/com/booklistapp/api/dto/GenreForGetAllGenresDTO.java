package com.booklistapp.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GenreForGetAllGenresDTO {
    @NotBlank(message = "GenreDTO name may not be empty!")
    @JsonProperty("genre")
    private String name;
}
