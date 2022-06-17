package com.boivalenko.businessapp.web.app.repository;

import com.boivalenko.businessapp.web.app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
