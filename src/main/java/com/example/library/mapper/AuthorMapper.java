package com.example.library.mapper;

import com.example.library.dto.AuthorDto;
import com.example.library.entity.Author;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toEntity(AuthorDto authorDto);

    List<AuthorDto> toDtoList(List<Author> authors);
}