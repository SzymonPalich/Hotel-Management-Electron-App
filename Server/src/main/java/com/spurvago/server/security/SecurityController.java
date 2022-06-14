package com.spurvago.server.security;

import com.spurvago.server.security.models.Role;
import com.spurvago.server.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Endpointy do autentykacji
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/auth")
public class SecurityController {
    private final UserManager userManager;

    @Autowired
    public SecurityController(UserManager userManager) {
        this.userManager = userManager;
    }

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials loginCredentials) {

    }

    @GetMapping(path = "/user")
    public User getUser() {
        return userManager.getUser();
    }

    @GetMapping(path = "/user/role")
    public Role getUserRole() {
        return new Role(userManager.getRole());
    }
}
