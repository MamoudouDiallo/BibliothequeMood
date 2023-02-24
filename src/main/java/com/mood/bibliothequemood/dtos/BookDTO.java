package com.mood.bibliothequemood.dtos;


import com.mood.bibliothequemood.entites.enums.Language;


public class BookDTO {
    private Long id;
    private String title;
    private  String image;
    private String description;
    private Integer pageNumber;
    private Language language;


    public BookDTO() {
    }

    public BookDTO(Long id,
                   String title,
                   String description,
                   Integer pageNumber,
                   Language language,
                   String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pageNumber = pageNumber;
        this.language = language;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }
}
