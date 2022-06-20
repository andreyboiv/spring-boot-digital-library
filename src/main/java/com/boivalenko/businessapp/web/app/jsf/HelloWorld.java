package com.boivalenko.businessapp.web.app.jsf;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Named;

@Getter
@Setter
@Named
public class HelloWorld {

    private String firstName = "John";
    private String lastName = "Doe";

    public String showGreeting() {
        return "Hello " + firstName + " " + lastName + "!";
    }

}
