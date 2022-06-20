package com.boivalenko.businessapp.web.app.spring.controller;

import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import com.boivalenko.businessapp.web.app.spring.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/save")
    public ResponseEntity<Genre> save(@RequestBody Genre genre) {
        return this.genreService.save(genre);
    }

    @PutMapping("/update")
    public ResponseEntity<Genre> update(@RequestBody Genre genre) {
        return this.genreService.update(genre);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Genre> deleteById(@PathVariable("id") Long id) {
        return this.genreService.deleteById(id);
    }

    @PostMapping("/findById")
    public ResponseEntity<Genre> findById(@RequestBody Long id) {
        return this.genreService.findById(id);
    }

    @PostMapping("/findAll")
    public ResponseEntity<List<Genre>> findAll() {
        return this.genreService.findAll();
    }

    @PostMapping("/findGenresByNameContainingIgnoreCaseOrderByNameAsc")
    public ResponseEntity<List<Genre>> findGenresByNameContainingIgnoreCase(@RequestBody String name) {
        return this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

}
