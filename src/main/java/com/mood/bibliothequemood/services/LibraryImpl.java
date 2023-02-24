package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.LibraryDTO;
import com.mood.bibliothequemood.entites.LibraryEntity;
import com.mood.bibliothequemood.mappers.LibraryMapper;
import com.mood.bibliothequemood.repositories.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryImpl implements LibraryService{

    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibraryMapper libraryMapper;
    @Override
    public LibraryDTO addLibrary(LibraryDTO libraryDTO) {
        LibraryEntity libraryEntity = libraryMapper.toEntity(libraryDTO);
        LibraryEntity savedLibrary = libraryRepository.save(libraryEntity);
        return libraryMapper.toDto(savedLibrary);
    }

    @Override
    public LibraryDTO editLibrary(LibraryDTO libraryDTO, Long id) {
        Optional<LibraryEntity> library = libraryRepository.findById(id);

        if (library.isPresent()) {
            LibraryEntity libraryEntity = libraryMapper.toEntity(libraryDTO);
            LibraryEntity savedLibrary = libraryRepository.save(libraryEntity);
            return libraryMapper.toDto(savedLibrary);
        }
        return new LibraryDTO();
    }

    @Override
    public List<LibraryEntity> getLibraries() {
        return libraryRepository.findAll();
    }

    @Override
    public LibraryEntity getLibrary(Long id) {
        Optional<LibraryEntity> library = libraryRepository.findById(id);

        if (library.isPresent()) {
            return library.get();
        }
        return new LibraryEntity();
    }

    @Override
    public void deleteLibrary(Long id) {
        libraryRepository.deleteById(id);
    }
}
