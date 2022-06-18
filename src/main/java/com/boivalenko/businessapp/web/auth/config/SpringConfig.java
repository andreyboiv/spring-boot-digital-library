package com.boivalenko.businessapp.web.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Temporär muss Spring Basic Sicherheit
        // ausgeschaltet werden, damit kein Login Fenster erscheint
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and()
                .csrf().disable();
                return http.build();
    }
}