package com.bosch.example.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bosch.example.dto.User;

@RestController
public class TestController {
    
    @GetMapping("/test")
    public String test(@RequestBody User user) {
        if (user.name() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is missing.");
        }

        return user.name();
    }

    @GetMapping("/exc")
    public String getMethodName(@RequestBody User user) {
        if (parseIntOrNull(user.password()) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "password need to contain only integer numbers.");
        }

        return user.password();
    }

    public Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}