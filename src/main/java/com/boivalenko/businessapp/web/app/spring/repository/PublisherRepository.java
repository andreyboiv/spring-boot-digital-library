package com.boivalenko.businessapp.web.app.spring.repository;

import com.boivalenko.businessapp.web.app.spring.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findPublishersByNameContainingIgnoreCaseOrderByNameAsc(String name);
}
