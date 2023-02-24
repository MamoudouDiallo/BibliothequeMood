package com.mood.bibliothequemood.repositories;

import com.mood.bibliothequemood.entites.LibraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryEntity, Long> {
}
