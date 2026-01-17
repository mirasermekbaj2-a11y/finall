package com.example.library.service;

import com.example.library.entity.Genre;
import java.util.List;

public interface GenreService {
    List<Genre> getAll();
    void addGenre(Genre genre);
    void updateGenre(Long id, Genre genre);
    Genre getById(Long id);
}