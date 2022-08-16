package com.boivalenko.businessapp.web.app.spring;

import com.boivalenko.businessapp.web.app.spring.controller.AuthorController;
import com.boivalenko.businessapp.web.app.spring.controller.GenreController;
import com.boivalenko.businessapp.web.app.spring.controller.PublisherController;
import com.boivalenko.businessapp.web.app.spring.controller.SpringBookController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

//check for any important eagerly initialized bean

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class BackendApplicationTests {

    @Autowired
    AuthorController authorController;
    @Autowired
    GenreController genreController;
    @Autowired
    PublisherController publisherController;
    @Autowired
    SpringBookController springBookController;

    @Test
    public void contextLoads() {
        Assertions.assertThat(authorController).isNotNull();
        Assertions.assertThat(genreController).isNotNull();
        Assertions.assertThat(publisherController).isNotNull();
        Assertions.assertThat(springBookController).isNotNull();
    }
}
