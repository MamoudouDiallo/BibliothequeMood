package com.mood.bibliothequemood.mappers;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookEntity toEntity(BookDTO bookDTO);
    BookDTO toDto(BookEntity bookEntity);
}
