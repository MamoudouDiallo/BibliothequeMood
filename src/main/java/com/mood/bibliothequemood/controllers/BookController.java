package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/books/{id}")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        return bookService.updateBook(bookDTO, id);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookEntity>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }


    @GetMapping("/books/{id}")
    public ResponseEntity<BookEntity> getBook(@PathVariable Long id) throws BookNotFoundException {
        return ResponseEntity.ok(bookService.getBook(id));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


}
