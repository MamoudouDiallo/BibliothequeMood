package com.mood.bibliothequemood.services;

import com.mood.bibliothequemood.dtos.AuthorDTO;
import com.mood.bibliothequemood.entites.AuthorEntity;
import com.mood.bibliothequemood.mappers.AuthorMapper;
import com.mood.bibliothequemood.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorImpl implements AuthorService{

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;
    @Override
    public AuthorDTO addAuthor(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = authorMapper.toEntity(authorDTO);
        AuthorEntity savedAuthor = authorRepository.save(authorEntity);
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public AuthorDTO editAuthor(AuthorDTO authorDTO, Long id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);

        if (author.isPresent()) {
            AuthorEntity authorEntity = authorMapper.toEntity(authorDTO);
            AuthorEntity newAuthor = authorRepository.save(authorEntity);
            return authorMapper.toDto(newAuthor);
        }
        return new AuthorDTO();
    }

    @Override
    public List<AuthorEntity> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public AuthorEntity getAuthor(Long id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);

        if (author.isPresent()) {
            return author.get();
        }
        return new AuthorEntity();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
