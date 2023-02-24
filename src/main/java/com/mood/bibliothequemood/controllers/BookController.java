package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @PutMapping("/books/{id}")
    public BookDTO upadteBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        return bookService.updateBook(bookDTO, id);
    }

    @GetMapping("/books")
    public List<BookEntity> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/books/author/{id}")
    public List<BookEntity> getAuthorBook( @PathVariable Long id) {
        return bookService.getBooksByAuthor(id);
    }

    @GetMapping("/books/{id}")
    public BookEntity getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


}
