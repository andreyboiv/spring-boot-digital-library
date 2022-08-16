package com.boivalenko.businessapp.web.app.spring.controller;

import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import com.boivalenko.businessapp.web.app.spring.service.GenreService;
import com.google.gson.Gson;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Disable Spring Security for tests. To exclude security
// filters in your MockMvc tests,
// set @AutoConfigureMockMvc(addFilters = false)

@ExtendWith(SpringExtension.class)
@WebMvcTest(GenreController.class)
@AutoConfigureMockMvc(addFilters = false)
class GenreControllerTest {

    @MockBean
    GenreService genreService;

    @Autowired
    MockMvc mockMvc;

    //.andExpect(...) - assertThat einstellung
    @Test
    void save() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.save(genre))
                .thenReturn(response);
        String content = new Gson().toJson(genre);

        MvcResult mvcResult = this.mockMvc.perform(post("/genre/save")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpectAll(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                       , status().isOk()
                )
                .andReturn();

        verify(this.genreService, times(1)).save(genre);
        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = new Gson().fromJson(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName());
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Test
    void update() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.update(genre))
                .thenReturn(response);
        String content = new Gson().toJson(genre);

        MvcResult mvcResult = this.mockMvc.perform(put("/genre/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpectAll(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                        , status().isOk()
                )
                .andReturn();

        verify(this.genreService, times(1)).update(genre);
        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = new Gson().fromJson(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName());
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Test
    void deleteById() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        Long id = 1L;
        genre.setId(id);
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.deleteById(id))
                .thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(delete("/genre/deleteById/{id}", id)
                   .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpectAll(
                    content().contentType(MediaType.APPLICATION_JSON_UTF8),
                    status().isOk()
                )
                .andReturn();

        verify(this.genreService, times(1)).deleteById(id);
        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = new Gson().fromJson(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName());
        assertEquals(genreJson.getId(), genre.getId());
        assertEquals(HttpStatus.OK, httpStatus);
    }

    @Test
    void findById() throws Exception {
        HttpStatus httpStatus = HttpStatus.OK;
        Long id = 1L;
        Genre genre = new Genre();
        String musterGenre = "MusterGenre";
        genre.setName(musterGenre);
        genre.setId(id);
        when(this.genreService.findById(id))
                .thenReturn(new ResponseEntity<>(genre, httpStatus));
        String content = new Gson().toJson(id);

        MvcResult mvcResult = this.mockMvc.perform(post("/genre/findById")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpectAll(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8)
                        , status().isOk()
                )
                .andReturn();

        verify(this.genreService, times(1)).findById(id);
        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = new Gson().fromJson(contentAsString, Genre.class);
        assertEquals(genreJson.getId(), genre.getId());
        assertEquals(HttpStatus.OK, httpStatus);
        }

    @Test
    void findByIdGet() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        Long id = 1L;
        genre.setId(id);
        HttpStatus httpStatus = HttpStatus.OK;
        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.findById(id))
                .thenReturn(response);

        MvcResult mvcResult = this.mockMvc.perform(get("/genre/findByIdGet/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andDo(print())
                .andExpectAll(
                        content().contentType(MediaType.APPLICATION_JSON_UTF8),
                        status().isOk()
                )
                .andReturn();

        verify(this.genreService, times(1)).findById(id);
        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = new Gson().fromJson(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName());
        assertEquals(genreJson.getId(), genre.getId());
        assertEquals(HttpStatus.OK, httpStatus);}

    @Test
    void findAll() throws Exception {
        HttpStatus httpStatus = HttpStatus.OK;
        List<Genre> genres = Arrays.asList(new Genre(), new Genre(), new Genre());
        ResponseEntity<List<Genre>> response = new ResponseEntity<>(genres, httpStatus);
        when(this.genreService.findAll()).thenReturn(response);
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/genre/findAll");
        MvcResult mvcResult = mockMvc.perform(post)
                .andExpect(status().isOk()).andReturn();
        assertEquals(response.getBody().size(), (Integer) JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.length()"));
        assertEquals(httpStatus, HttpStatus.OK);
        verify(this.genreService, times(1)).findAll();
    }

    @Test
    void findAllGet() throws Exception {
        HttpStatus httpStatus = HttpStatus.OK;

        Genre genre1 = new Genre();
        String genre11 = "genre1";
        genre1.setName(genre11);

        Genre genre2 = new Genre();
        String genre22 = "genre2";
        genre2.setName(genre22);

        List<Genre> genres = Arrays.asList(genre1, genre2);
        ResponseEntity<List<Genre>> response = new ResponseEntity<>(genres, httpStatus);
        when(this.genreService.findAll()).thenReturn(response);
        MockHttpServletRequestBuilder getRequest =
                MockMvcRequestBuilders.get("/genre/findAllGet");

        MvcResult mvcResult = mockMvc.perform(getRequest
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(Encoding.DEFAULT_CHARSET))
        .andReturn();
        assertEquals(response.getBody().size(), (Integer) JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.length()"));
        assertEquals(httpStatus, HttpStatus.OK);
        verify(this.genreService, times(1)).findAll();
    }

    @Test
    void findGenresByNameContainingIgnoreCase() throws Exception {
        HttpStatus httpStatus = HttpStatus.OK;
        String musterName = "MusterName";
        List<Genre> genres = Arrays.asList(new Genre());
        ResponseEntity<List<Genre>> response = new ResponseEntity<>(genres, httpStatus);
        when(this.genreService.findGenresByNameContainingIgnoreCaseOrderByNameAsc(musterName)).thenReturn(response);
        MvcResult mvcResult = mockMvc.perform(post("/genre/findGenresByNameContainingIgnoreCaseOrderByNameAsc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(Encoding.DEFAULT_CHARSET)
                        .content(musterName)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        verify(this.genreService, times(1)).findGenresByNameContainingIgnoreCaseOrderByNameAsc(musterName);
        assertEquals(response.getBody().size(), (Integer) JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.length()"));
        assertEquals(httpStatus, HttpStatus.OK);
    }
}