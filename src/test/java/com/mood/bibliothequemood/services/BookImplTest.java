package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.mappers.BookMapper;
import com.mood.bibliothequemood.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookImpl bookService;

    @Test
    void testCreateBook() {
        // Arrange
        BookDTO bookDTO = new BookDTO();
        BookEntity bookEntity = new BookEntity();
        when(bookMapper.toEntity(bookDTO)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDTO);

        // when
        BookDTO createdBook = bookService.createBook(bookDTO);

        // then
        assertNotNull(createdBook);
        verify(bookRepository).save(bookEntity);
        verify(bookMapper).toDto(bookEntity);
    }

    @Test
    void testUpdateBook() throws BookNotFoundException {
        // Arrange
        Long bookId = 1L;
        BookDTO bookDTO = new BookDTO();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
        when(bookMapper.toEntity(bookDTO)).thenReturn(bookEntity);
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDTO);

        // when
        BookDTO updatedBook = bookService.updateBook(bookDTO, bookId);

        // then
        assertNotNull(updatedBook);
        verify(bookRepository).findById(bookId);
        verify(bookMapper).toEntity(bookDTO);
        verify(bookRepository).save(bookEntity);
        verify(bookMapper).toDto(bookEntity);
    }

    @Test
    void testGetBooks() {
        // Arrange
        List<BookEntity> bookEntities = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(bookEntities);

        // when
        List<BookDTO> retrievedBooks = bookService.getBooks();

        // then
        assertNotNull(retrievedBooks);
        verify(bookRepository).findAll();
    }

   /*
   If we do the check to verify that the book we want to recover exists

   @Test
    void testGetBook() throws BookNotFoundException {
        // Arrange
        Long bookId = 1L;
        BookEntity bookEntity = new BookEntity();
        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);

        // when
        BookEntity retrievedBook = bookService.getBook(bookId);

        // then
        assertNotNull(retrievedBook);
        verify(bookRepository).findById(bookId);
    }
    */

    @Test
    void testGetBook_returnsBookDto_whenBookExists() throws BookNotFoundException {
        Long id = 1L;
        BookEntity bookEntity = new BookEntity();
        BookDTO bookDTO = new BookDTO();

        when(bookRepository.findById(id)).thenReturn(Optional.of(bookEntity));
        when(bookMapper.toDto(bookEntity)).thenReturn(bookDTO);

        BookDTO result = bookService.getBook(id);

        assertEquals(bookDTO, result);
    }

    @Test
    void testGetBook_throwsBookNotFoundException_whenBookDoesNotExist() {
        Long id = 1L;

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> {
            bookService.getBook(id);
        });
    }

    @Test
    void testGetBookThrowsException() {
        // Arrange
        Long bookId = 1L;
        Optional<BookEntity> optionalBookEntity = Optional.empty();
        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);

        // when & then
        assertThrows(BookNotFoundException.class, () -> bookService.getBook(bookId));
        verify(bookRepository).findById(bookId);
    }

    /*
    If we do the check  to verify that the book we want to delete exists

    @Test
    void testDeleteBook() {
        // Arrange
        Long bookId = 1L;

        // when
        bookService.deleteBook(bookId);

        // then
        verify(bookRepository).deleteById(bookId);
    }
    */

    @Test
    void testDeleteBook_WhenBookExists() throws BookNotFoundException {
        // Arrange
        long bookId = 1L;
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(bookId);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        BookImpl bookService = new BookImpl(bookRepository, null);

        // when
        bookService.deleteBook(bookId);

        // then
        verify(bookRepository, times(1)).deleteById(bookId);
    }

    @Test
    void testDeleteBook_WhenBookDoesNotExist() {
        // Arrange
        long bookId = 1L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        BookImpl bookService = new BookImpl(bookRepository, null);

        // when & then
        assertThrows(BookNotFoundException.class, () -> {
            bookService.deleteBook(bookId);
        });
    }
}
