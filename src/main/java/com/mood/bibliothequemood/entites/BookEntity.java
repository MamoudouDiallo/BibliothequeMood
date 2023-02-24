package com.mood.bibliothequemood.entites;

import com.mood.bibliothequemood.entites.enums.Language;
import lombok.*;
import org.apache.tomcat.jni.Library;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;

    private String image;
    private String editor;
    private String gender;
    private Integer pageNumber;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
    @ManyToOne(targetEntity = LibraryEntity.class)
    @JoinColumn(name = "library_id")
    private LibraryEntity library;

    public BookEntity() {
    }

    public BookEntity(Long id,
                      String title,
                      String description,
                      LocalDate publicationDate,
                      String editor,
                      String gender,
                      Integer pageNumber,
                      Language language,
                      AuthorEntity author,
                      LibraryEntity library,
                      String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.editor = editor;
        this.gender = gender;
        this.pageNumber = pageNumber;
        this.language = language;
        this.author = author;
        this.library = library;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public LibraryEntity getLibrary() {
        return library;
    }

    public void setLibrary(LibraryEntity library) {
        this.library = library;
    }
}
