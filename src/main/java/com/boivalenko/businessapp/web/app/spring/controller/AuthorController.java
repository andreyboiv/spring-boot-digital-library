package com.boivalenko.businessapp.web.app.spring.controller;

import com.boivalenko.businessapp.web.app.spring.entities.Author;
import com.boivalenko.businessapp.web.app.spring.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<Author> save(@RequestBody Author author) {
        return this.authorService.save(author);
    }

    @PutMapping("/update")
    public ResponseEntity<Author> update(@RequestBody Author author) {
        return this.authorService.update(author);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Author> deleteById(@PathVariable("id") Long id) {
        return this.authorService.deleteById(id);
    }

    @PostMapping("/findById")
    public ResponseEntity<Author> findById(@RequestBody Long id) {
        return this.authorService.findById(id);
    }

    @PostMapping("/findAll")
    public ResponseEntity<List<Author>> findAll() {
        return this.authorService.findAll();
    }

}
