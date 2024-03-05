package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.exception.BookNotFoundException;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO authorDTO);
    BookDTO updateBook(BookDTO authorDTO, Long id) throws BookNotFoundException;
    List<BookDTO> getBooks();

    void deleteAll();
    BookDTO getBook(Long id) throws BookNotFoundException;
    void deleteBook(Long id) throws BookNotFoundException;

}
