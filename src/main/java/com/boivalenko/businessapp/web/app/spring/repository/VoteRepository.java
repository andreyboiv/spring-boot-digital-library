package com.boivalenko.businessapp.web.app.spring.repository;

import com.boivalenko.businessapp.web.app.spring.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
}