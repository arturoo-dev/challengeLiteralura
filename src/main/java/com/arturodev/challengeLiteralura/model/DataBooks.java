package com.arturodev.challengeLiteralura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.OptionalInt;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "books")
public class DataBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String languages;
    private Integer downloadCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private DataAuthor author;

    public DataBooks(String title, DataAuthor author, List<String> lenguages, Integer downloadCount) {
        this.title = title;
        this.author = author;
        this.languages = lenguages != null && !lenguages.isEmpty() ? String.join(",", lenguages) : null;
        this.downloadCount = OptionalInt.of(downloadCount).orElse(0);
    }
}
