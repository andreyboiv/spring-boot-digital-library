package com.boivalenko.businessapp.web.app.jsf.controller;

import com.boivalenko.businessapp.web.app.jsf.enums.SearchType;
import com.boivalenko.businessapp.web.app.jsf.model.LazyDataTable;
import com.boivalenko.businessapp.web.app.spring.entities.Book;
import com.boivalenko.businessapp.web.app.spring.service.BookService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;


@Named
@SessionScoped
@Component
@Getter
@Setter
public class BookController implements AbstractController<Book> {

    //Wie viel Bücher werden angezeigt auf der Seite
    public static final int DEFAULT_PAGE_SIZE = 5;

    //Es müssen Verweise auf Variablen aus der JSF-Tabelle vorhanden sein, sonst funktioniert das DataGrid bei Verwendung der Paginierung nicht richtig (funktioniert nicht mit der Bean)
    // der vom Benutzer gewählte Wert (Anzahl der Datensätze pro Seite) wird gespeichert
    private int rowsCount = DEFAULT_PAGE_SIZE;

    //merkt sich die zuletzt gewählte Suchoption
    private SearchType searchType;

    private BookService bookService;


    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    //eine Hilfs-Klasse, die hilft,
    // Paging auf die Seite zu realisieren und die
    // Seiten richtig anzuzeigen (funktioniert
    // zusammen mit Komponenten auf einer JSF-Seite)
    private LazyDataTable<Book> lazyModel;

    //Liste der gefundenen Bücher
    private Page<Book> bookPages;

    //Nachdem BookController Bean wird erzeugt, wird die Methode aufgerufen
    @PostConstruct
    public void init() {
        this.lazyModel = new LazyDataTable(this);
    }

    //Method wird automatisch von LazyDataTable
    // aufgerufen
    @Override
    public Page<Book> search (int first, int count, String sortField, Sort.Direction sortDirection) {
        if (sortField == null) {
            sortField = "name";
        }

        if (searchType == null) {
            this.bookPages = this.bookService.findAllWithoutContent(first, count, sortField, sortDirection);
            List<Book> content = this.bookPages.getContent();
            for (Book book : content) {
                book.setImageAsString();
            }
        } else {
            switch (searchType) {
                case SEARCH_GENRE:
                    //TODO
                    break;
                case SEARCH_TEXT:
                    //TODO
                    break;
                case ALL:
                    this.bookPages = this.bookService.findAllWithoutContent(first, count, sortField, sortDirection);
                    List<Book> content = this.bookPages.getContent();
                    for (Book book : content) {
                        book.setImageAsString();
                    }
                    break;
            }
        }
        return bookPages;
    }

}
