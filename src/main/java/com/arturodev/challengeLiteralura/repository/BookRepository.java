package com.arturodev.challengeLiteralura.repository;

import com.arturodev.challengeLiteralura.model.DataBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<DataBooks, Long> {

}
