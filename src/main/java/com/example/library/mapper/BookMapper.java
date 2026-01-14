package com.example.library.mapper;

import com.example.library.dto.BookDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class, AuthorMapper.class})
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto bookDto);

    List<BookDto> toDtoList(List<Book> books);
}