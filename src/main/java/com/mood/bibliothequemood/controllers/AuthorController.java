package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.AuthorDTO;
import com.mood.bibliothequemood.entites.AuthorEntity;
import com.mood.bibliothequemood.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/author")
    public AuthorDTO addAuthor(@RequestBody AuthorDTO author) {
        return authorService.addAuthor(author);
    }

    @PutMapping("/author/{id}")
    public AuthorDTO editAuthor(@RequestBody AuthorDTO auhtor, @PathVariable Long id) {
        return authorService.editAuthor(auhtor, id);
    }

    @GetMapping("/author")
    public List<AuthorEntity> getAuthor() {
        return authorService.getAuthors();
    }

    @GetMapping("/author/{id}")
    public AuthorEntity getAuthors( @PathVariable Long id) {
        return authorService.getAuthor(id);
    }

    @DeleteMapping("/author/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
