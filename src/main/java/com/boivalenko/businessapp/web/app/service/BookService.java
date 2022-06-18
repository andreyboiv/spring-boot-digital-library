package com.boivalenko.businessapp.web.app.service;

import com.boivalenko.businessapp.web.app.entities.Book;
import com.boivalenko.businessapp.web.app.repository.BookRepository;
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
public class BookService implements IBaseService<Book> {

    private final BookRepository bookRepository;

    @Override
    public ResponseEntity<Book> save(Book book) {
        if (book.getId() != null) {
            return new ResponseEntity("ID wird automatisch generiert. Man muss das nicht eingeben",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (book.getName().isEmpty() || book.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.bookRepository.save(book));
    }

    @Override
    public ResponseEntity<Book> update(Book book) {
        if (book.getId() == null || book.getId() == 0) {
            return new ResponseEntity("ID darf weder NULL noch 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (book.getName().isEmpty() || book.getName().toLowerCase().contains("null")) {
            return new ResponseEntity("NAME darf weder NULL noch leer sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.bookRepository.existsById(book.getId()) == false) {
            return new ResponseEntity("ID=" + book.getId() + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(this.bookRepository.save(book));
    }

    @Override
    public ResponseEntity<Book> deleteById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.bookRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        this.bookRepository.deleteById(id);
        return new ResponseEntity("Book mit ID=" + id + " erfolgreich gelöscht", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> findById(Long id) {
        if (id == 0) {
            return new ResponseEntity("ID darf nicht 0 sein",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        if (this.bookRepository.existsById(id) == false) {
            return new ResponseEntity("ID=" + id + " nicht gefunden",
                    HttpStatus.NOT_ACCEPTABLE);
        }

        Book book = this.bookRepository.findById(id).get();
        return ResponseEntity.ok(book);
    }

    @Override
    public ResponseEntity<List<Book>> findAll() {
        List<Book> all = this.bookRepository.findAll();
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Book vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<List<Book>> findBooksByNameOrAuthorNameOrderByName(String bookTitel, String authorName) {

        if (bookTitel == null || bookTitel.trim().equals("")) {
            bookTitel = null;
        }

        if (authorName == null || authorName.trim().equals("")) {
            authorName = null;
        }

        List<Book> all = this.bookRepository.findBooksByNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCaseOrderByName(bookTitel, authorName);
        if (all == null || all.isEmpty()) {
            return new ResponseEntity("gar kein Book vorhanden",
                    HttpStatus.OK);
        }
        return ResponseEntity.ok(all);
    }
}
