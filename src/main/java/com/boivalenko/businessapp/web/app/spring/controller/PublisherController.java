package com.boivalenko.businessapp.web.app.spring.controller;

import com.boivalenko.businessapp.web.app.spring.entities.Publisher;
import com.boivalenko.businessapp.web.app.spring.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/save")
    public ResponseEntity<Publisher> save(@RequestBody Publisher publisher) {
        return this.publisherService.save(publisher);
    }

    @PutMapping("/update")
    public ResponseEntity<Publisher> update(@RequestBody Publisher publisher) {
        return this.publisherService.update(publisher);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Publisher> deleteById(@PathVariable("id") Long id) {
        return this.publisherService.deleteById(id);
    }

    @PostMapping("/findById")
    public ResponseEntity<Publisher> findById(@RequestBody Long id) {
        return this.publisherService.findById(id);
    }

    @PostMapping("/findAll")
    public ResponseEntity<List<Publisher>> findAll() {
        return this.publisherService.findAll();
    }

    @PostMapping("/findPublishersByNameContainingIgnoreCaseOrderByNameAsc")
    public ResponseEntity<List<Publisher>> findPublishersByNameContainingIgnoreCaseOrderByNameAsc(@RequestBody String name) {
        return this.publisherService.findPublishersByNameContainingIgnoreCaseOrderByNameAsc(name);
    }
}
