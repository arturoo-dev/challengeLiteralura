package com.arturodev.challengeLiteralura.repository;

import com.arturodev.challengeLiteralura.model.DataAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorsRepository extends JpaRepository<DataAuthor, Long> {

    DataAuthor findByNameContainsIgnoreCase(String name);

    @Query("SELECT author FROM DataAuthor author " +
            "WHERE author.birth_year <= :year AND (author.death_year IS NULL OR author.death_year >= :year)")
    List<DataAuthor> findByDeathYear(Integer year);
}
