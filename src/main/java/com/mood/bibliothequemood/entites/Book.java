package com.mood.bibliothequemood.entites;

import com.mood.bibliothequemood.entites.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Library;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDate publicationDate;
    private String editor;
    private String gender;
    private Integer pageNumber;
    @Enumerated(EnumType.STRING)
    private Language language;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @ManyToOne(targetEntity = com.mood.bibliothequemood.entites.Library.class)
    @JoinColumn(name = "library_id")
    private Library library;
}
