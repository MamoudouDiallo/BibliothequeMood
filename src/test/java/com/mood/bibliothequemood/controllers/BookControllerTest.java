package com.mood.bibliothequemood.controllers;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.services.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Test
    void testCreateBook() {
        // Arrange
        BookDTO bookDTO = new BookDTO();
        BookDTO createdBookDTO = new BookDTO();
        when(bookService.createBook(eq(bookDTO))).thenReturn(createdBookDTO);

        // when
        ResponseEntity<BookDTO> responseEntity = bookController.createBook(bookDTO);

        // then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdBookDTO, responseEntity.getBody());
    }

    @Test
    void testUpdateBook() throws BookNotFoundException {
        // Arrange
        Long id = 1L;
        BookDTO bookDTO = new BookDTO();
        BookDTO updatedBookDTO = new BookDTO();
        when(bookService.updateBook(eq(bookDTO), eq(id))).thenReturn(updatedBookDTO);

        // when
        BookDTO result = bookController.updateBook(bookDTO, id);

        // then
        assertEquals(updatedBookDTO, result);
    }

    @Test
    void getBook_returnsBookDto_whenBookExists() throws BookNotFoundException {
        // Arrange
        Long bookId = 1L;
        BookDTO expectedBookDto = new BookDTO();

        when(bookService.getBook(bookId)).thenReturn(expectedBookDto);

        // when
        ResponseEntity<BookDTO> response = bookController.getBook(bookId);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedBookDto, response.getBody());
    }

    @Test
    void getBook_throwsBookNotFoundException_whenBookDoesNotExist() throws BookNotFoundException {
        // Arrange
        Long bookId = 1L;

        when(bookService.getBook(bookId)).thenThrow(new BookNotFoundException("Book not found with id: " + bookId));

        // when and then
        assertThrows(BookNotFoundException.class, () -> bookController.getBook(bookId));
    }


    @Test
    void testDeleteBook() throws BookNotFoundException {
        // Arrange
        Long id = 1L;

        // when
        bookController.deleteBook(id);

        // then
        verify(bookService, times(1)).deleteBook(eq(id));
    }
}
