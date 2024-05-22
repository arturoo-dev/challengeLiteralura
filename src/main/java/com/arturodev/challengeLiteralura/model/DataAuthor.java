package com.arturodev.challengeLiteralura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class DataAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DataBooks> books = new ArrayList<>();


    public DataAuthor(){}

    public DataAuthor(Author author) {
        this.name = author.name();
        this.birth_year = author.birthYear();
        this.death_year = author.deathYear();
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

    public Integer getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(Integer birth_year) {
        this.birth_year = birth_year;
    }

    public Integer getDeath_year() {
        return death_year;
    }

    public void setDeath_year(Integer death_year) {
        this.death_year = death_year;
    }

    public List<DataBooks> getBooks() {
        return books;
    }

    public void setBooks(List<DataBooks> books) {
        this.books = books;
    }
}

