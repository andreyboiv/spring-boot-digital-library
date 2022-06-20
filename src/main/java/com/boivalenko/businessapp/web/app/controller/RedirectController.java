package com.boivalenko.businessapp.web.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.ManagedBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@Controller
public class RedirectController {

    @GetMapping
    public String baseUrlRedirect(HttpServletRequest request, HttpServletResponse response) {
        return "redirect:" + request.getRequestURL().append("index.xhtml").toString();
    }
}
