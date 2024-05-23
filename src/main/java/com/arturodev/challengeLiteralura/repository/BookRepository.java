package com.arturodev.challengeLiteralura.repository;

import com.arturodev.challengeLiteralura.model.DataBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<DataBooks, Long> {

    @Query("SELECT book FROM DataBooks book " +
            "WHERE book.languages = :languages")
    DataBooks findByLanguages(String languages);
}
