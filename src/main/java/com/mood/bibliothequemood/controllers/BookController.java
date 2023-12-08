package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/api/v1/rest/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) throws BookNotFoundException {
        return bookService.updateBook(bookDTO, id);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getBooks() {
        return ResponseEntity.ok(bookService.getBooks());
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable Long id) throws BookNotFoundException {
        try {
            return ResponseEntity.ok(bookService.getBook(id));
        } catch (BookNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id) throws BookNotFoundException {
//        bookService.deleteBook(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            bookService.deleteBook(id);
            return ResponseEntity.noContent().build();
        } catch (BookNotFoundException e) {
            // Handle BookNotFoundException by returning a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteAll() {
        try {
            bookService.deleteAll();
            return ResponseEntity.ok("success delete all");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(("error deleting"));
        }
    }

}
