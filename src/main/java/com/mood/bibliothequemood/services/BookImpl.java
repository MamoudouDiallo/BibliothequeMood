package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.mappers.BookMapper;
import com.mood.bibliothequemood.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookMapper bookMapper;
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity bookEntity = bookMapper.toEntity(bookDTO);
        BookEntity createdBookEntity = bookRepository.save(bookEntity);
        return bookMapper.toDto(createdBookEntity);
    }

    @Override
    public BookDTO updateBook(BookDTO authorDTO, Long id) {
        Optional<BookEntity> authorEntity = bookRepository.findById(id);

        if (authorEntity.isPresent()) {
            BookEntity newBookEntity = bookMapper.toEntity(authorDTO);
            BookEntity savedBookEntity = bookRepository.save(newBookEntity);
            return bookMapper.toDto(savedBookEntity);
        }
        return new BookDTO();
    }

    @Override
    public List<BookEntity> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> getBooksByAuthor(Long id) {
        return bookRepository.findByAuthorId(id);
    }

    @Override
    public BookEntity getBook(Long id) {
        Optional<BookEntity> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        }
        return new BookEntity();
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
