package com.spurvago.server.security;

import com.spurvago.server.security.models.UserRole;
import com.spurvago.server.security.models.User;
import com.spurvago.server.security.models.UserId;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/auth")
public class PermissionController {
    private final UserManager userManager;

    @Autowired
    public PermissionController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping(path = "/user")
    public User getUser() {
        return userManager.GetUser();
    }

    @GetMapping(path = "/user/id")
    public UserId getUserId() {
        return userManager.GetUserId();
    }

    @GetMapping(path = "/user/role")
    public UserRole getUserRole() {
        return userManager.GetUserRole();
    }
}