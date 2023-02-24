package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;

import java.util.List;

public interface BookService {

    BookDTO createBook(BookDTO authorDTO);
    BookDTO updateBook(BookDTO authorDTO, Long id);
    List<BookEntity> getBooks();
    List<BookEntity> getBooksByAuthor(Long id);
    BookEntity getBook(Long id);
    void deleteBook(Long id);

}
