package com.mood.bibliothequemood.dtos;

import com.mood.bibliothequemood.entites.BookEntity;

import java.util.List;

public class LibraryDTO {
    private Long id;
    private String name;


    public LibraryDTO() {
    }

    public LibraryDTO(Long id, String name, List<BookEntity> books) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
