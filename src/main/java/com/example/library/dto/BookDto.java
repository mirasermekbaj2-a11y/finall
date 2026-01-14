package com.example.library.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookDto {
    private Long id;
    private String title;
    private GenreDto genre;
    private List<AuthorDto> authors;
}