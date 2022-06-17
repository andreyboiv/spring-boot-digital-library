package com.boivalenko.businessapp.web.app.repository;

import com.boivalenko.businessapp.web.app.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
