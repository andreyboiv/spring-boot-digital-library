package com.boivalenko.businessapp.web.app.spring.service;

import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import com.boivalenko.businessapp.web.app.spring.repository.GenreRepository;
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
public class GenreService implements IBaseService<Genre> {

    private final GenreRepository genreRepository;

    @Override
    public ResponseEntity<Genre> save(Genre genre) {
        if (genre.getId() != null) {
            return new ResponseEntity("ID wird automatisch generiert. Man muss das nicht eingeben",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getName().isEmpty() || genre.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.genreRepository.save(genre));
    }

    @Override
    public ResponseEntity<Genre> update(Genre genre) {
        if (genre.getId() == null || genre.getId() == 0) {
            return new ResponseEntity("ID darf weder NULL noch 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getName().isEmpty() || genre.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.genreRepository.existsById(genre.getId()) == false) {
            return new ResponseEntity("ID=" + genre.getId() + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.genreRepository.save(genre));
    }

    @Override
    public ResponseEntity<Genre> deleteById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.genreRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        this.genreRepository.deleteById(id);
        return new ResponseEntity("Genre mit ID=" + id + " erfolgreich gelöscht", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Genre> findById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.genreRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Genre genre = this.genreRepository.findById(id).get();
        return ResponseEntity.ok(genre);
    }

    @Override
    public ResponseEntity<List<Genre>> findAll() {
        List<Genre> all = this.genreRepository.findAll();
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Genre vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<List<Genre>> findGenresByNameContainingIgnoreCaseOrderByNameAsc(String name) {
        List<Genre> all = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Genre vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }
}
