package com.mood.bibliothequemood.services.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mood.bibliothequemood.dtos.BookDTO;
import com.mood.bibliothequemood.entites.BookEntity;
import com.mood.bibliothequemood.exception.BookNotFoundException;
import com.mood.bibliothequemood.mappers.BookMapper;
import com.mood.bibliothequemood.repositories.BookRepository;
import com.mood.bibliothequemood.services.BookImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookImpl bookService;

    @Test
    void createBook() {
        // Given
        // Initialiser avec les données nécessaires ou creation d'instance
        BookDTO bookDTO = new BookDTO();
        BookEntity newBookEntity = new BookEntity();

        // Comportement simulé avec Mockito
        when(bookMapper.toEntity(bookDTO)).thenReturn(newBookEntity);
        when(bookRepository.save(newBookEntity)).thenReturn(newBookEntity);
        when(bookMapper.toDto(newBookEntity)).thenReturn(bookDTO);

        // When
        // Appeler la méthode à tester
        BookDTO result = bookService.createBook(bookDTO);

        assertEquals(bookDTO, result);

        // Then
        // Assertions basées sur le comportement attendu
        assertNotNull(result);
        // Ajouter des assertions en fonction de votre logique métier
    }


    @Test
    void updateBook() throws BookNotFoundException {
        // Given
        // Identifiant d'un livre existant
        Long id = 1L;
        // Données mises à jour du livre
        BookDTO updatedBookDTO = new BookDTO();
        // Données existantes du livre
        BookEntity existingBookEntity = new BookEntity();

        // Comportement simulé avec Mockito
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBookEntity));
        //when(bookMapper.toEntity(updatedBookDTO)).thenReturn(existingBookEntity);
        when(bookRepository.save(existingBookEntity)).thenReturn(existingBookEntity);
        when(bookMapper.toDto(existingBookEntity)).thenReturn(updatedBookDTO);

        // When
        // Appeler la méthode à tester
        BookDTO result = bookService.updateBook(updatedBookDTO, id);

        assertEquals(updatedBookDTO, result);

        // Then
        // Assertions basées sur le comportement attendu
        assertNotNull(result);
        // Ajouter des assertions en fonction de votre logique métier
    }

    @Test
    void getBooks() {
        // Given
        // Liste de livres pour les tests
        List<BookEntity> bookEntities = Arrays.asList(new BookEntity(), new BookEntity());

        // Comportement simulé avec Mockito
        when(bookRepository.findAll()).thenReturn(bookEntities);
        when(bookMapper.toDtoList(bookEntities)).thenReturn(Arrays.asList(new BookDTO(), new BookDTO()));

        // When
        // Appeler la méthode à tester
        List<BookDTO> result = bookService.getBooks();

        // Then
        // Assertions basées sur le comportement attendu
        assertNotNull(result);
        // Ajouter des assertions en fonction de votre logique métier
    }

    @Test
    void getBook() throws BookNotFoundException {
        // Given
        // Identifiant d'un livre existant
        Long id = 1L;
        // Données existantes du livre
        BookEntity existingBookEntity = new BookEntity();

        // Comportement simulé avec Mockito
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBookEntity));
        when(bookMapper.toDto(existingBookEntity)).thenReturn(new BookDTO());

        // When
        // Appeler la méthode à tester
        BookDTO result = bookService.getBook(id);

        // Then
        // Assertions basées sur le comportement attendu
        assertNotNull(result);
        // Ajouter des assertions en fonction de votre logique métier
    }

    @Test
    void deleteBook()    {
        // Given
        // Identifiant d'un livre existant
        Long id = 1L;
        // Données existantes du livre
        BookEntity existingBookEntity = new BookEntity();

        // Comportement simulé avec Mockito
        when(bookRepository.findById(id)).thenReturn(Optional.of(existingBookEntity));

        // When
        // Appeler la méthode à tester
        assertDoesNotThrow(() -> bookService.deleteBook(id));

        // Then
        // Ajouter des assertions en fonction de votre logique métier
    }
}
