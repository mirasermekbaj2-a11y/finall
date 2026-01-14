package com.example.library.controller;

import com.example.library.dto.GenreDto;
import com.example.library.entity.Genre;
import com.example.library.mapper.GenreMapper;
import com.example.library.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAll() {
        List<Genre> genres = genreService.getAll();
        return new ResponseEntity<>(genreMapper.toDtoList(genres), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> addGenre(@RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        genreService.addGenre(genre);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGenre(@PathVariable(name = "id") Long id,
                                            @RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        genreService.updateGenre(id, genre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getById(@PathVariable(name = "id") Long id) {
        Genre genre = genreService.getById(id);
        return new ResponseEntity<>(genreMapper.toDto(genre), HttpStatus.OK);
    }
}