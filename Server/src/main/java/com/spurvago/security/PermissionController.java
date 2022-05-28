package com.spurvago.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
public class PermissionController {

    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    @ResponseBody
    public Collection<? extends GrantedAuthority> getUserAuthorities(Authentication authentication) {
        return authentication.getAuthorities();
    }
}