package com.arturodev.challengeLiteralura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalInt;

@Entity
@Table(name = "books")
public class DataBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String languages;
    private Integer downloadCount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private DataAuthor author;

    public DataBooks() {
    }

    public DataBooks(Book book) {
        this.title = book.title();
        this.languages = book.languages().get(0);
        this.downloadCount = book.downloadCount();
        this.author = new DataAuthor(book.authors().get(0));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataAuthor getAuthor() {
        return author;
    }

    public void setAuthor(DataAuthor author) {
        this.author = author;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return """
                -----------📖 LIBRO 📖-----------
                Titulo 📚: %s
                Autor 🤵🏼‍: %s
                Idioma 🌎: %s
                Numero de Descargas 📤: %s
                ---------------------------------
                """.formatted(this.title, this.author.getName(), this.languages, this.downloadCount);
    }
}

