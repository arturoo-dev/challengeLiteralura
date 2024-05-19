package com.arturodev.challengeLiteralura.repository;

import com.arturodev.challengeLiteralura.model.DataAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsRepository extends JpaRepository<DataAuthor, Long> {
}
