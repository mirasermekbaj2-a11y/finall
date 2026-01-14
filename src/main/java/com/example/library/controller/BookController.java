package com.example.library.controller;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import com.example.library.mapper.BookMapper;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAll() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(bookMapper.toDtoList(books), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable(name = "id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(bookMapper.toDto(book), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        Book bookEntity = bookMapper.toEntity(bookDto);
        Book createdBook = bookService.createBook(bookEntity);
        return new ResponseEntity<>(bookMapper.toDto(createdBook), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable(name = "id") Long id,
                                              @RequestBody BookDto bookDto) {
        Book bookEntity = bookMapper.toEntity(bookDto);
        Book updatedBook = bookService.updateBook(id, bookEntity);
        return new ResponseEntity<>(bookMapper.toDto(updatedBook), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(name = "id") Long id) {
        if (bookService.deleteBookById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}