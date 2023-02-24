package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.LibraryDTO;
import com.mood.bibliothequemood.entites.LibraryEntity;
import com.mood.bibliothequemood.services.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/library")
    public LibraryDTO addLibrary(@RequestBody LibraryDTO library) {
        return libraryService.addLibrary(library);
    }

    @PutMapping("/library/{id}")
    public LibraryDTO editLibrary(@RequestBody LibraryDTO library, @PathVariable Long id) {
        return libraryService.editLibrary(library, id);
    }

    @GetMapping("/library")
    public List<LibraryEntity> getLibrary() {
        return libraryService.getLibraries();
    }

    @GetMapping("/library/{id}")
    public LibraryEntity getLibraries( @PathVariable Long id) {
        return libraryService.getLibrary(id);
    }

    @DeleteMapping("/library/{id}")
    public void deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
    }
}
