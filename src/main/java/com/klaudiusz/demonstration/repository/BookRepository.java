package com.klaudiusz.demonstration.repository;

import com.klaudiusz.demonstration.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}