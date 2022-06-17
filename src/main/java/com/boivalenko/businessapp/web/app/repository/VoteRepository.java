package com.boivalenko.businessapp.web.app.repository;

import com.boivalenko.businessapp.web.app.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}