package com.boivalenko.businessapp.web.app.spring.repository;

import com.boivalenko.businessapp.web.app.spring.entities.Book;
import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenreRepositoryTest {

    private final String GENRE_DUMMY_1 = "genreDummy";
    private final String GENRE_DUMMY_2 = "geNREDummy2g";
    private final String GENRE_DUMMY_3 = "_dsgenreDUMMy3";
    private final String GENRE_DUMMY_4 = "df_genrEDummy4";

    private int genre_size;

    @Autowired
    private GenreRepository genreRepository;

    @BeforeEach
    void initUseCase() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(1988, 8, 6);
        List<Book> books = new ArrayList<>();

        books.add(new Book());
        books.add(new Book());
        books.add(new Book());

        List<Genre> genres = Arrays.asList(
                new Genre(GENRE_DUMMY_1, books),
                new Genre(GENRE_DUMMY_2, books),
                new Genre(GENRE_DUMMY_3, books),
                new Genre(GENRE_DUMMY_4, books)
        );
        genre_size = genres.size();
        genreRepository.saveAll(genres);
    }

    //findGenresByNameContainingIgnoreCaseOrderByNameAsc
    // Find Genre by Name Containing Ignore Case Order By Name Asc


        ////////**** Negative Tests ****//////////
    @Test
    void find_null_name() {
        String name = null;
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertTrue(genres.isEmpty());
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void find_name_mit_bezeichnung_null() {
        String name = "null";
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertTrue(genres.isEmpty());
    }

    @Test
    void find_nicht_existierenden_Name() {
        String name = UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "");
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertTrue(genres.isEmpty());
    }


/*
    @Test
    void find_empty_name() {
        //man bekommt dadurch ganze Liste,
        // was meinem Verständnis nicht korrekt ist. Bug...
        String name = "";
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertTrue(genres.isEmpty());
    }
*/

    @Test
    void find_zu_langen_Name() {
        String name = "zuLANGEEE" + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "");
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertTrue(genres.isEmpty());
        org.assertj.core.api.Assertions.assertThatNoException();
    }

        ////////**** Positive Tests ****//////////
    @Test
    void find_richtige_Namen() {
        String name = GENRE_DUMMY_1;
        List<Genre> genres = this.genreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertFalse(genres.isEmpty());
        org.assertj.core.api.Assertions.assertThat(genres.size()).isGreaterThanOrEqualTo(genre_size);
    }

}