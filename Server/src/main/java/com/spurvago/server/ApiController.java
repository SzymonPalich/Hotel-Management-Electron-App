package com.spurvago.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Do testów
 */
@RestController
@RequestMapping
public class ApiController {

    @GetMapping
    public String status() {
        return "Ok";
    }
}