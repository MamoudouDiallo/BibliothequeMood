package com.mood.bibliothequemood.mappers;

import com.mood.bibliothequemood.dtos.AuthorDTO;
import com.mood.bibliothequemood.entites.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorEntity toEntity(AuthorDTO authorDTO);
    AuthorDTO toDto(AuthorEntity authorEntity);
}
