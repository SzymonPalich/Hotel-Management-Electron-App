package com.spurvago.server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api")
public class ApiController {

    @GetMapping
    public String status() {
        return "Ok";
    }
}
