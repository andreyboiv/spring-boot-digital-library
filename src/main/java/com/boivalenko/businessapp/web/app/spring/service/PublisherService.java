package com.boivalenko.businessapp.web.app.spring.service;

import com.boivalenko.businessapp.web.app.spring.entities.Publisher;
import com.boivalenko.businessapp.web.app.spring.repository.PublisherRepository;
import com.boivalenko.businessapp.web.base.IBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PublisherService implements IBaseService<Publisher> {

    private final PublisherRepository publisherRepository;

    @Override
    public ResponseEntity<Publisher> save(Publisher publisher) {
        if (publisher.getId() != null) {
            return new ResponseEntity("ID wird automatisch generiert. Man muss das nicht eingeben",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (publisher.getName().isEmpty() || publisher.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.publisherRepository.save(publisher));
    }

    @Override
    public ResponseEntity<Publisher> update(Publisher publisher) {
        if (publisher.getId() == null || publisher.getId() == 0) {
            return new ResponseEntity("ID darf weder NULL noch 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (publisher.getName().isEmpty() || publisher.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.publisherRepository.existsById(publisher.getId()) == false) {
            return new ResponseEntity("ID=" + publisher.getId() + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.publisherRepository.save(publisher));
    }

    @Override
    public ResponseEntity<Publisher> deleteById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.publisherRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        this.publisherRepository.deleteById(id);
        return new ResponseEntity("Publisher mit ID=" + id + " erfolgreich gelöscht", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Publisher> findById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.publisherRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Publisher publisher = this.publisherRepository.findById(id).get();
        return ResponseEntity.ok(publisher);
    }

    @Override
    public ResponseEntity<List<Publisher>> findAll() {
        List<Publisher> all = this.publisherRepository.findAll();
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Publisher vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<List<Publisher>> findPublishersByNameContainingIgnoreCaseOrderByNameAsc(String name) {
        List<Publisher> all = this.publisherRepository.findPublishersByNameContainingIgnoreCaseOrderByNameAsc(name);
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Publisher vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }
}
