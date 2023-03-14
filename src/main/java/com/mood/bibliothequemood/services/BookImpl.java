package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.mappers.BookMapper;
import com.mood.bibliothequemood.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        BookEntity newBookEntity = bookMapper.toEntity(bookDTO);
        BookEntity addedNewBookEntity = bookRepository.save(newBookEntity);
        return bookMapper.toDto(addedNewBookEntity);
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, Long id) throws BookNotFoundException {
        Optional<BookEntity> bookExists = bookRepository.findById(id);

        if (bookExists.isPresent()) {
            BookEntity updatedBookEntity = bookExists.get();
            bookMapper.toEntity(bookDTO);
            bookRepository.save(updatedBookEntity);
            return bookMapper.toDto(updatedBookEntity);
        }
        throw new BookNotFoundException("Book not found with id: " + id);
    }

    @Override
    public List<BookDTO> getBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookMapper.toDtoList(bookEntities);
    }

    @Override
    public BookDTO getBook(Long id) throws BookNotFoundException {
        Optional<BookEntity> bookExists = bookRepository.findById(id);

        if (bookExists.isPresent()) {
            BookEntity bookEntity = bookExists.get();
            return bookMapper.toDto(bookEntity);
        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    @Override
    public void deleteBook(Long id) throws BookNotFoundException {
        Optional<BookEntity> bookExists = bookRepository.findById(id);

        if (bookExists.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

}
