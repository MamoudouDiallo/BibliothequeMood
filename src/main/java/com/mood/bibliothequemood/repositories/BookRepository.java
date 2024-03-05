package com.mood.bibliothequemood.repositories;

import com.mood.bibliothequemood.entites.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
