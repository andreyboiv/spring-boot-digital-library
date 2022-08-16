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

    public static final String GAR_KEIN_GENRE_VORHANDEN = "gar kein Genre vorhanden";
    public static final String ID_WIRD_AUTOMATISCH_GENERIERT_MAN_MUSS_DAS_NICHT_EINGEBEN = "ID wird automatisch generiert. Man muss das nicht eingeben";
    public static final String NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN = "NAME darf weder NULL noch leer sein";
    public static final String GENRE_DARF_NICHT_NULL_SEIN = "Genre darf nicht null sein";
    public static final String ID_DARF_WEDER_NULL_NOCH_0_SEIN = "ID darf weder NULL noch 0 sein";
    private final GenreRepository genreRepository;

    @Override
    public ResponseEntity<Genre> save(Genre genre) {
        if (genre == null) {
            return new ResponseEntity(GENRE_DARF_NICHT_NULL_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getId() != null) {
            return new ResponseEntity(ID_WIRD_AUTOMATISCH_GENERIERT_MAN_MUSS_DAS_NICHT_EINGEBEN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getName() == null || genre.getName().isEmpty() || genre.getName().toLowerCase().contains("null")) {
            return new ResponseEntity(NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.genreRepository.save(genre));
    }

    @Override
    public ResponseEntity<Genre> update(Genre genre) {
        if (genre == null) {
            return new ResponseEntity(GENRE_DARF_NICHT_NULL_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getId() == null || genre.getId() == 0) {
            return new ResponseEntity(ID_DARF_WEDER_NULL_NOCH_0_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (genre.getName() == null || genre.getName().isEmpty() || genre.getName().toLowerCase().contains("null")) {
            return new ResponseEntity(NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.genreRepository.existsById(genre.getId()) == false) {
            return new ResponseEntity("ID=" + genre.getId() + " nicht gefunden",
                    HttpStatus.OK);
        }

        return ResponseEntity.ok(this.genreRepository.save(genre));
    }

    @Override
    public ResponseEntity<Genre> deleteById(Long id) {
        if (id == null || id == 0) {
            return new ResponseEntity(ID_DARF_WEDER_NULL_NOCH_0_SEIN,
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
        if (id == null || id == 0) {
            return new ResponseEntity(ID_DARF_WEDER_NULL_NOCH_0_SEIN,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.genreRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.OK);
        }

        Genre genre = this.genreRepository.findById(id).get();
        return ResponseEntity.ok(genre);
    }

    @Override
    public ResponseEntity<List<Genre>> findAll() {
        List<Genre> all = this.genreRepository.findAll();
        if (all == null || all.isEmpty()) {
            return new ResponseEntity(GAR_KEIN_GENRE_VORHANDEN,
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<List<Genre>> findGenresByNameContainingIgnoreCaseOrderByNameAsc(String name) {
        List<Genre> all = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        if (all == null || all.isEmpty()) {
            return new ResponseEntity(GAR_KEIN_GENRE_VORHANDEN,
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }

}
