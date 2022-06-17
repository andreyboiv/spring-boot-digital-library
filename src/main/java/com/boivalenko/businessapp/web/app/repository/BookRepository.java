package com.boivalenko.businessapp.web.app.repository;

import com.boivalenko.businessapp.web.app.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
