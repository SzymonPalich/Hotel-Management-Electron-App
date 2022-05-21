package com.spurvago.server.security;

import com.spurvago.security.LoginCredentials;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials) {

    }
}
