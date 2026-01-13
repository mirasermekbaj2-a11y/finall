package com.example.library.service.impl;

import com.example.library.entity.Genre;
import com.example.library.repository.GenreRepository;
import com.example.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Long id, Genre genre) {
        Genre updateGenre = genreRepository.findById(id).orElse(null);
        if (updateGenre != null) {
            updateGenre.setName(genre.getName());
            genreRepository.save(updateGenre);
        }
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }
}