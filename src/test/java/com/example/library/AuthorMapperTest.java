package com.example.library;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import com.example.library.mapper.AuthorMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AuthorMapperTest {

    @Autowired
    private AuthorMapper authorMapper;

    @Test
    void convertEntityToDtoTest() {
        Author entityAuthor = new Author(1L, "George Orwell");

        AuthorDto dtoAuthor = authorMapper.toDto(entityAuthor);

        Assertions.assertNotNull(dtoAuthor);
        Assertions.assertNotNull(dtoAuthor.getId());
        Assertions.assertNotNull(dtoAuthor.getName());

        Assertions.assertEquals(entityAuthor.getId(), dtoAuthor.getId());
        Assertions.assertEquals(entityAuthor.getName(), dtoAuthor.getName());
    }

    @Test
    void convertDtoToEntityTest() {
        AuthorDto dtoAuthor = new AuthorDto();
        dtoAuthor.setId(1L);
        dtoAuthor.setName("George Orwell");

        Author entityAuthor = authorMapper.toEntity(dtoAuthor);

        Assertions.assertNotNull(entityAuthor);
        Assertions.assertNotNull(entityAuthor.getId());
        Assertions.assertNotNull(entityAuthor.getName());

        Assertions.assertEquals(dtoAuthor.getId(), entityAuthor.getId());
        Assertions.assertEquals(dtoAuthor.getName(), entityAuthor.getName());
    }

    @Test
    void convertListEntityToListDtoTest() {
        List<Author> entityList = new ArrayList<>();
        entityList.add(new Author(1L, "Author 1"));
        entityList.add(new Author(2L, "Author 2"));
        entityList.add(new Author(3L, "Author 3"));

        List<AuthorDto> dtoList = authorMapper.toDtoList(entityList);

        Assertions.assertNotNull(dtoList);

        Assertions.assertNotEquals(0, dtoList.size());

        Assertions.assertEquals(entityList.size(), dtoList.size());

        for (int i = 0; i < entityList.size(); i++) {
            Author entityAuthor = entityList.get(i);
            AuthorDto dtoAuthor = dtoList.get(i); // Берем соответствующий элемент из результата

            Assertions.assertNotNull(dtoAuthor);
            Assertions.assertNotNull(dtoAuthor.getId());
            Assertions.assertNotNull(dtoAuthor.getName());

            Assertions.assertEquals(entityAuthor.getId(), dtoAuthor.getId());
            Assertions.assertEquals(entityAuthor.getName(), dtoAuthor.getName());
        }
    }
}