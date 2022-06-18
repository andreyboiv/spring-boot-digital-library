package com.boivalenko.businessapp.web.app.controller;

import com.boivalenko.businessapp.web.app.entities.Book;
import com.boivalenko.businessapp.web.app.search.BookSearchValues;
import com.boivalenko.businessapp.web.app.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/findById")
    public ResponseEntity<Book> findById(@RequestBody Long id) {
        return this.bookService.findById(id);
    }

    @PostMapping("/findAll")
    public ResponseEntity<List<Book>> findAll() {
        return this.bookService.findAll();
    }

    @PostMapping("/findBooksByNameOrAuthorNameOrderByName")
    public ResponseEntity<List<Book>> findBooksByNameOrAuthorNameOrderByName(@RequestBody BookSearchValues bookSearchValues) {
        return this.bookService.findBooksByNameOrAuthorNameOrderByName(bookSearchValues.getBookTitel(), bookSearchValues.getAuthorName());
    }

}
