package com.boivalenko.businessapp.web.app.service;

import com.boivalenko.businessapp.web.app.entities.Author;
import com.boivalenko.businessapp.web.app.repository.AuthorRepository;
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
public class AuthorService implements IBaseService<Author> {

    private final AuthorRepository authorRepository;

    @Override
    public ResponseEntity<Author> save(Author author) {
        if (author.getId() != null) {
            return new ResponseEntity("ID wird automatisch generiert. Man muss das nicht eingeben",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (author.getName().isEmpty() || author.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.authorRepository.save(author));
    }

    @Override
    public ResponseEntity<Author> update(Author author) {
        if (author.getId() == null || author.getId() == 0) {
            return new ResponseEntity("ID darf weder NULL noch 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (author.getName().isEmpty() || author.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.authorRepository.existsById(author.getId()) == false) {
            return new ResponseEntity("ID=" + author.getId() + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.authorRepository.save(author));
    }

    @Override
    public ResponseEntity<Author> deleteById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.authorRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        this.authorRepository.deleteById(id);
        return new ResponseEntity("Author mit ID=" + id + " erfolgreich gelöscht", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Author> findById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.authorRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Author author = this.authorRepository.findById(id).get();
        return ResponseEntity.ok(author);
    }

    @Override
    public ResponseEntity<List<Author>> findAll() {
        List<Author> all = this.authorRepository.findAll();
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Author vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }
}
