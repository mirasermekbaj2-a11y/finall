package com.example.library.mapper;

import com.example.library.dto.GenreDto;
import com.example.library.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toDtoList(List<Genre> genres);
}