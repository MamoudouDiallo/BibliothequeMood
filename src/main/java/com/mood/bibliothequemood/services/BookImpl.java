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
        Optional<BookEntity> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            BookEntity existingBookEntity = bookOptional.get();

            // Appliquer les modifications du DTO à l'entité existante
            existingBookEntity.setTitle(bookDTO.getTitle());
            existingBookEntity.setImage(bookDTO.getImage());
            existingBookEntity.setDescription(bookDTO.getDescription());
            existingBookEntity.setAuthor(bookDTO.getAuthor());
            existingBookEntity.setLibrary(bookDTO.getLibrary());
            existingBookEntity.setPageNumber(bookDTO.getPageNumber());
            existingBookEntity.setLanguage(bookDTO.getLanguage());

            // Sauvegarder l'entité mise à jour dans la base de données
            bookRepository.save(existingBookEntity);

            // Retourner le DTO correspondant à l'entité mise à jour
            return bookMapper.toDto(existingBookEntity);
        }

        throw new BookNotFoundException("Book not found with id: " + id);
    }


    @Override
    public List<BookDTO> getBooks() {
        List<BookEntity> bookEntities = bookRepository.findAll();
        return bookMapper.toDtoList(bookEntities);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
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
        Optional<BookEntity> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }


}
