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

    @PostMapping("/book")
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @PutMapping("/book/{id}")
    public BookDTO upadteBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        return bookService.updateBook(bookDTO, id);
    }

    @GetMapping("/book")
    public List<BookEntity> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/book/author/{id}")
    public List<BookEntity> getAuthorBook( @PathVariable Long id) {
        return bookService.getBooksByAuthor(id);
    }

    @GetMapping("/book/{id}")
    public BookEntity getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }


}
