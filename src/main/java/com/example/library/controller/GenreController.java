package com.example.library.controller;

import com.example.library.entity.Genre;
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

    @GetMapping
    public ResponseEntity<List<Genre>> getAll() {
        return new ResponseEntity<>(genreService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addGenre(@RequestBody Genre genre) {
        genreService.addGenre(genre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGenre(@PathVariable(name = "id") Long id,
                                         @RequestBody Genre genre) {
        genreService.updateGenre(id, genre);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> getById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(genreService.getById(id), HttpStatus.OK);
    }
}