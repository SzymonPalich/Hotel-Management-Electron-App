package com.spurvago.server.security;

import lombok.Getter;

/**
 * Klasa panelu logowania
 */
@Getter
public class LoginCredentials {
    private String email;
    private String password;
}
