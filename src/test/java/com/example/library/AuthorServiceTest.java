package com.example.library;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.mapper.AuthorMapper;
import com.example.library.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void getAllAuthorsServiceTest() {
        Author author = new Author(null, "Test Author");
        authorService.createAuthor(author);

        List<Author> authors = authorService.getAllAuthors();
        List<AuthorDto> authorDtoList = authorMapper.toDtoList(authors);

        Assertions.assertNotNull(authorDtoList);
        Assertions.assertNotEquals(0, authorDtoList.size());

        for (AuthorDto dto : authorDtoList) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getName());
        }
    }

    @Test
    void getByIdAuthorTest() {
        Author author = new Author(null, "Search Author");
        Author saved = authorService.createAuthor(author);

        Author found = authorService.getAuthorById(saved.getId());
        AuthorDto foundDto = authorMapper.toDto(found);

        Assertions.assertNotNull(foundDto);
        Assertions.assertEquals(saved.getId(), foundDto.getId());
        Assertions.assertEquals("Search Author", foundDto.getName());

        Author testAuthor = authorService.getAuthorById(-1L);
        Assertions.assertNull(testAuthor);
    }

    @Test
    void addAuthorTest() {
        Author author = new Author();
        author.setName("New Writer");

        Author createdAuthor = authorService.createAuthor(author);
        AuthorDto createdDto = authorMapper.toDto(createdAuthor);

        Assertions.assertNotNull(createdDto);
        Assertions.assertNotNull(createdDto.getId());
        Assertions.assertEquals("New Writer", createdDto.getName());

        Author checkAuthor = authorService.getAuthorById(createdDto.getId());
        Assertions.assertNotNull(checkAuthor);
        Assertions.assertEquals(createdDto.getId(), checkAuthor.getId());
    }

    @Test
    void updateAuthorTest() {
        Author author = new Author(null, "Old Name");
        Author saved = authorService.createAuthor(author);

        Author updateData = new Author();
        updateData.setName("Updated Name");

        Author updated = authorService.updateAuthor(saved.getId(), updateData);
        AuthorDto updatedDto = authorMapper.toDto(updated);

        Assertions.assertNotNull(updatedDto);
        Assertions.assertEquals("Updated Name", updatedDto.getName());

        Author checkAuthor = authorService.getAuthorById(saved.getId());
        Assertions.assertEquals("Updated Name", checkAuthor.getName());
    }

    @Test
    void deleteAuthorTest() {
        Author author = new Author(null, "To Delete");
        Author saved = authorService.createAuthor(author);
        Long id = saved.getId();

        authorService.deleteAuthor(id);

        Author deleted = authorService.getAuthorById(id);
        Assertions.assertNull(deleted);
    }
}