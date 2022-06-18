package com.boivalenko.businessapp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//SpringBootServletInitializer is an interface to
// run SpringApplication from a traditional WAR deployment.
// It binds Servlet, Filter and ServletContextInitializer beans from the application context to the server.

@SpringBootApplication
public class DigitaleBibliothekApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DigitaleBibliothekApplication.class, args);
    }

}
