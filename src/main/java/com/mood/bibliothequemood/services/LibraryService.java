package com.mood.bibliothequemood.services;



import com.mood.bibliothequemood.dtos.LibraryDTO;
import com.mood.bibliothequemood.entites.LibraryEntity;

import java.util.List;

public interface LibraryService {

    LibraryDTO addLibrary(LibraryDTO libraryDTO);
    LibraryDTO editLibrary(LibraryDTO libraryDTO, Long id);

    List<LibraryEntity> getLibraries();
    LibraryEntity getLibrary(Long id);
    void deleteLibrary(Long id);
}
