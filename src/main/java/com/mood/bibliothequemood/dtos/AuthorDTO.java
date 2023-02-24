package com.mood.bibliothequemood.dtos;

import com.mood.bibliothequemood.entites.BookEntity;

import java.util.List;

public class AuthorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String country;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String lastName, String country, List<BookEntity> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
