package com.spurvago.server.security;

import com.spurvago.security.LoginCredentials;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/auth")
public class SecurityController {
    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials) {

    }
}
