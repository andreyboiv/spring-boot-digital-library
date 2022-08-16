package com.boivalenko.businessapp.web.app.spring.service;

import com.boivalenko.businessapp.web.app.spring.entities.Book;
import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import com.boivalenko.businessapp.web.app.spring.repository.GenreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @InjectMocks
    private GenreService genreService;

    @Mock
    private GenreRepository mockedGenreRepository;

    private final String GENRE_DUMMY_1 = "genreDummy";
    private final String GENRE_DUMMY_2 = "geNREDummy2g";
    private final String GENRE_DUMMY_3 = "_dsgenreDUMMy3";
    private final String GENRE_DUMMY_4 = "df_genrEDummy4";


    //findGenresByNameContainingIgnoreCaseOrderByNameAsc
    // Find Genre by Name Containing Ignore Case Order By Name Asc


        ////////**** Negative Tests ****//////////
    @Test
    void find_null_name() {
        String name = null;
        when(this.mockedGenreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name)).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity<List<Genre>> genres = this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, genres.getBody());
        verify(mockedGenreRepository, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void find_name_mit_bezeichnung_null() {
        String name = "null";
        when(this.mockedGenreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name)).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity<List<Genre>> genres = this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        verify(mockedGenreRepository, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, genres.getBody());
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void find_nicht_existierenden_Name() {
        String name = UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "");
        when(this.mockedGenreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name)).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity<List<Genre>> genres = this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, genres.getBody());
        verify(mockedGenreRepository, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void find_zu_langen_Name() {
        String name = "zuLANGEEE" + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "")
                + UUID.randomUUID().toString().replaceAll("_", "");
        when(this.mockedGenreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name)).thenReturn(Collections.EMPTY_LIST);
        ResponseEntity<List<Genre>> genres = this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, genres.getBody());
        verify(mockedGenreRepository, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(name);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

        ////////**** Positive Tests ****//////////
    @Test
    void find_richtige_Namen() {
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

        when(this.mockedGenreRepository.findGenresByNameContainingIgnoreCaseOrderByNameAsc(GENRE_DUMMY_1)).thenReturn(genres);

        ResponseEntity<List<Genre>> genresResponse = this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(GENRE_DUMMY_1);
        Assertions.assertFalse(genresResponse.getBody().isEmpty());
        org.assertj.core.api.Assertions.assertThat(genresResponse.getBody().size()).isGreaterThanOrEqualTo(genres.size());
        verify(mockedGenreRepository, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(GENRE_DUMMY_1);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //save

        //Negativ

    @Test
    void save_NullGenre() {
        Genre genre = null;
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(GenreService.GENRE_DARF_NICHT_NULL_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void save_genreId_eingeben() {
        Genre genre = new Genre();
        genre.setId(1L);
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(GenreService.ID_WIRD_AUTOMATISCH_GENERIERT_MAN_MUSS_DAS_NICHT_EINGEBEN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void save_nameNull_eingeben() {
        Genre genre = new Genre();
        genre.setName(null);
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void save_nameContainNull_eingeben() {
        Genre genre = new Genre();
        genre.setName("BlanUlLBlaB");
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void save_nameLeer_eingeben() {
        Genre genre = new Genre();
        genre.setName("");
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }
        //Positiv

    @Test
    void save() {
        Genre genre = new Genre();
        genre.setName("GanzNormalerName");
        ResponseEntity<Genre> genres = this.genreService.save(genre);
        Assertions.assertEquals(HttpStatus.OK, genres.getStatusCode());
        verify(mockedGenreRepository, times(1)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //update

        //Negativ

    @Test
    void update_NullGenre() {
        Genre genre = null;
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.GENRE_DARF_NICHT_NULL_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void update_genreIdAls0_eingeben() {
        Genre genre = new Genre();
        genre.setId(0L);
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void update_genreIdAlsNull_eingeben() {
        Genre genre = new Genre();
        genre.setId(null);
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }


    @Test
    void update_nameNull_eingeben() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName(null);
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void update_nameContainNull_eingeben() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("BlanUlLBlaB");
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void update_nameLeer_eingeben() {
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("");
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(GenreService.NAME_DARF_WEDER_NULL_NOCH_LEER_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void update_nameID_exist_nicht() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        when(this.mockedGenreRepository.existsById(id)).thenReturn(false);
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals("ID=" + genre.getId() + " nicht gefunden", genres.getBody());
        verify(mockedGenreRepository, times(0)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

        //Positiv

    @Test
    void update() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        when(this.mockedGenreRepository.existsById(id)).thenReturn(true);
        ResponseEntity<Genre> genres = this.genreService.update(genre);
        Assertions.assertEquals(HttpStatus.OK, genres.getStatusCode());
        verify(mockedGenreRepository, times(1)).save(genre);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //deleteById

        //Negativ

    @Test
    void delete_genreIdAlsNull_eingeben() {
        Genre genre = new Genre();
        Long id = null;
        genre.setId(id);
        ResponseEntity<Genre> genres = this.genreService.deleteById(id);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).deleteById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void delete_genreIdAls0_eingeben() {
        Long id = 0L;
        ResponseEntity<Genre> genres = this.genreService.deleteById(id);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).deleteById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void delete_nameID_exist_nicht() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        when(this.mockedGenreRepository.existsById(id)).thenReturn(false);
        ResponseEntity<Genre> genres = this.genreService.deleteById(id);
        Assertions.assertEquals("ID=" + genre.getId() + " nicht gefunden", genres.getBody());
        verify(mockedGenreRepository, times(0)).deleteById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

        //Positiv
    @Test
    void deleteById() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        when(this.mockedGenreRepository.existsById(id)).thenReturn(true);
        ResponseEntity<Genre> genres = this.genreService.deleteById(id);
        Assertions.assertEquals(HttpStatus.OK, genres.getStatusCode());
        verify(mockedGenreRepository, times(1)).deleteById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //findById

    //Negativ

    @Test
    void findById_genreIdAlsNull_eingeben() {
        Genre genre = new Genre();
        Long id = null;
        genre.setId(id);
        ResponseEntity<Genre> genres = this.genreService.findById(id);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).findById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void findById_genreIdAls0_eingeben() {
        Long id = 0L;
        ResponseEntity<Genre> genres = this.genreService.findById(id);
        Assertions.assertEquals(GenreService.ID_DARF_WEDER_NULL_NOCH_0_SEIN, genres.getBody());
        verify(mockedGenreRepository, times(0)).findById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void findById_nameID_exist_nicht() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        when(this.mockedGenreRepository.existsById(id)).thenReturn(false);
        ResponseEntity<Genre> genres = this.genreService.findById(id);
        Assertions.assertEquals("ID=" + genre.getId() + " nicht gefunden", genres.getBody());
        verify(mockedGenreRepository, times(0)).findById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //Positiv
    @Test
    void findById() {
        Genre genre = new Genre();
        Long id = 1L;
        genre.setId(id);
        genre.setName("GanzNormalerName");
        Optional<Genre> genreOptional = Optional.of(genre);
        when(this.mockedGenreRepository.existsById(id)).thenReturn(true);
        doReturn(genreOptional).when(this.mockedGenreRepository).findById(id);
        ResponseEntity<Genre> genres = this.genreService.findById(id);
        Assertions.assertEquals(HttpStatus.OK, genres.getStatusCode());
        verify(mockedGenreRepository, times(1)).findById(id);
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //findAll

    //Negativ
    @Test
    void findAll_genres_null() {
        List<Genre> genres = null;
        when(this.mockedGenreRepository.findAll()).thenReturn(genres);
        ResponseEntity<List<Genre>> all = this.genreService.findAll();
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, all.getBody());
        verify(mockedGenreRepository, times(1)).findAll();
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    @Test
    void findAll_genres_empty() {
        List<Genre> genres = Collections.EMPTY_LIST;
        when(this.mockedGenreRepository.findAll()).thenReturn(genres);
        ResponseEntity<List<Genre>> all = this.genreService.findAll();
        Assertions.assertEquals(GenreService.GAR_KEIN_GENRE_VORHANDEN, all.getBody());
        verify(mockedGenreRepository, times(1)).findAll();
        org.assertj.core.api.Assertions.assertThatNoException();
    }

    //Positiv
    @Test
    void findAll() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre());
        when(this.mockedGenreRepository.findAll()).thenReturn(genres);
        ResponseEntity<List<Genre>> all = this.genreService.findAll();
        Assertions.assertEquals(HttpStatus.OK, all.getStatusCode());
        verify(mockedGenreRepository, times(1)).findAll();
        org.assertj.core.api.Assertions.assertThatNoException();

    }

}