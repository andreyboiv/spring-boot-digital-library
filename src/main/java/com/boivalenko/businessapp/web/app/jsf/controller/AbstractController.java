package com.boivalenko.businessapp.web.app.jsf.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

//Interface beschreibt
// das Verhalten von allen JSF Controllers
public interface AbstractController<T> extends Serializable {

    // Daten werden per Page rauskommen
    Page<T> search(int first, int count, String sortField, Sort.Direction sortDirection);
}
