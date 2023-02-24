package com.mood.bibliothequemood.services;


import com.mood.bibliothequemood.dtos.AuthorDTO;
import com.mood.bibliothequemood.entites.AuthorEntity;

import java.util.List;

public interface AuthorService {

    AuthorDTO addAuthor(AuthorDTO authorDTO);
    AuthorDTO editAuthor(AuthorDTO bookDTO, Long id);

    List<AuthorEntity> getAuthors();
    AuthorEntity getAuthor(Long id);
    void deleteAuthor(Long id);
}
