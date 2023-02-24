package com.mood.bibliothequemood.mappers;

import com.mood.bibliothequemood.dtos.LibraryDTO;
import com.mood.bibliothequemood.entites.LibraryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LibraryMapper {

    LibraryEntity toEntity(LibraryDTO libraryDTO);
    LibraryDTO toDto(LibraryEntity libraryEntity);
}
