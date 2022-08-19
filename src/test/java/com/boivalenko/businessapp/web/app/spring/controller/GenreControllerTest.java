package com.boivalenko.businessapp.web.app.spring.controller;

import com.boivalenko.businessapp.web.app.spring.entities.Genre;
import com.boivalenko.businessapp.web.app.spring.service.GenreService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Disable Spring Security for tests. To exclude security
// filters in your MockMvc tests,
// set @AutoConfigureMockMvc(addFilters = false) or set
// @WebMvcTest(controllers = GenreController.class,
//        excludeAutoConfiguration = {SecurityAutoConfiguration.class})

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GenreController.class,
        excludeAutoConfiguration = {SecurityAutoConfiguration.class})
//@AutoConfigureMockMvc(addFilters = false)
class GenreControllerTest {

    @MockBean
    GenreService genreService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Genre can be saved")
    void save() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.save(genre))
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genre);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/genre/save")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = mapper.readValue(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName(),
                "The returned genre name is incorrect");
        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).save(genre);
    }

    @Test
    @DisplayName("Genre can be updated")
    void update() throws Exception {
        String musterGenre = "musterGenre";
        Genre genre = new Genre(musterGenre,null);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.update(genre))
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genre);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.put("/genre/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = mapper.readValue(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName(),
                "The returned genre name is incorrect");
        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).update(genre);
    }

    @Test
    @DisplayName("Genre can be deleted")
    void deleteById() throws Exception {
        String musterGenre = "musterGenre";
        Long id = 1L;
        Genre genre = new Genre(musterGenre,null);
        genre.setId(id);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.deleteById(id))
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genre);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.delete("/genre/deleteById/"+id)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = mapper.readValue(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName(),
                "The returned genre name is incorrect");
        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Genre can be finded")
    void findById() throws Exception {
        String musterGenre = "musterGenre";
        Long id = 1L;
        Genre genre = new Genre(musterGenre,null);
        genre.setId(id);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.findById(id))
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(id);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/genre/findById")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(mapper.writeValueAsString(genre)),
                        status().isOk()
                ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = mapper.readValue(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName(),
                "The returned genre name is incorrect");
        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Genre can be finded. Get Method")
    void findByIdGet() throws Exception {
        String musterGenre = "musterGenre";
        Long id = 1L;
        Genre genre = new Genre(musterGenre,null);
        genre.setId(id);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<Genre> response = new ResponseEntity<>(genre, httpStatus);
        when(this.genreService.findById(id))
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genre);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.get("/genre/findByIdGet/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString(Encoding.DEFAULT_CHARSET);
        Genre genreJson = mapper.readValue(contentAsString, Genre.class);
        assertEquals(genreJson.getName(), genre.getName(),
                "The returned genre name is incorrect");
        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Find all Genres")
    void findAll() throws Exception {
        String musterGenre = "musterGenre";
        Long id = 1L;
        Genre genre = new Genre(musterGenre,null);
        genre.setId(id);
        Genre genre2 = new Genre(musterGenre+"2",null);
        List genreList = List.of(genre, genre2);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<List<Genre>> response = new ResponseEntity<>(genreList, httpStatus);
        when(this.genreService.findAll())
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genreList);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.post("/genre/findAll/")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");
        verify(this.genreService, times(1)).findAll();
    }

    @Test
    @DisplayName("Find all Genres. Get Method")
    void findAllGet() throws Exception {
        String musterGenre = "musterGenre";
        Long id = 1L;
        Genre genre = new Genre(musterGenre,null);
        genre.setId(id);
        Genre genre2 = new Genre(musterGenre+"2",null);
        List genreList = List.of(genre, genre2);
        HttpStatus httpStatus = HttpStatus.OK;

        ResponseEntity<List<Genre>> response = new ResponseEntity<>(genreList, httpStatus);
        when(this.genreService.findAll())
                .thenReturn(response);
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(genreList);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder =
                MockMvcRequestBuilders.get("/genre/findAllGet")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(mockHttpServletRequestBuilder)
                .andDo(print())
                //.andExpect(...) ->> das sind assertThat Einstellungen
                .andExpectAll(
                        // TODO: klären ob's ein BUG wäre oder verstehe ich es nicht, wie es richtig funktioniert..
                        //  .  content().contentType(MediaType.APPLICATION_JSON),
                        content().encoding(Encoding.DEFAULT_CHARSET),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json(content),
                        status().isOk()
                ).andReturn();

        assertEquals(HttpStatus.OK, httpStatus,"HttpStatus is incorrect");

        verify(this.genreService, times(1)).findAll();
    }

    @Test
    @DisplayName("Find all Genres by Name")
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