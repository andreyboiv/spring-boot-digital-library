package com.boivalenko.businessapp.web.app.repository;

import com.boivalenko.businessapp.web.app.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
