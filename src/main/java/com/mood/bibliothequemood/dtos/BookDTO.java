package com.mood.bibliothequemood.dtos;


import com.mood.bibliothequemood.entites.enums.Language;


import javax.validation.constraints.*;


public class BookDTO {

    private Long id;
    @NotBlank(message = "Book title is required")
    @NotEmpty(message = "Book title must not be empty")
    private String title;
    @NotNull(message = "Book image is required ")
    @NotEmpty(message = "Book image must not be empty")
    private String image;
    @NotNull(message = "Book description is required")
    @NotEmpty(message = "Book description must not be empty")
    private String description;
    @NotNull(message = "author book is required")
    @NotEmpty(message = "Book author must not be empty")
    private String author;
    @NotNull(message = "Book library is required")
    @NotEmpty(message = "Book library must not be empty")
    private String library;
    @Min(value = 1, message = "Invalid Page number: Equals to zero or Less than zero")
    @Max(value = 50, message = "Invalid Page number: Exceeds 100 characters")
    private Integer pageNumber;
    private Language language;


    public BookDTO() {
    }

    public BookDTO(
            Long id,
            String title,
            String description,
            Integer pageNumber,
            Language language,
            String author,
            String library,
            String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.library = library;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
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
