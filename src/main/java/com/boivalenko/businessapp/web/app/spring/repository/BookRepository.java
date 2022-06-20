package com.boivalenko.businessapp.web.app.spring.repository;

import com.boivalenko.businessapp.web.app.spring.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCaseOrderByName(String bookTitel, String authorName);

    @Query("select new Book(b.id, b.image, b.name, b.pageCount, " +
            "b.isbn, b.publishYear, b.avgRating, b.totalVoteCount, " +
            "b.totalRating,b.viewCount,b.descr,b.genre,b.author,b.publisher) from Book b")
    Page<Book> findAllWithoutContent(PageRequest of);
}
