package com.spurvago.server.security;

import com.spurvago.server.security.models.User;
import com.spurvago.server.security.models.UserId;
import com.spurvago.server.security.models.UserRole;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Collection;

@Component
public class UserManager {
    public User GetUser() {
        throw new NotYetImplementedException();
    }

    public UserId GetUserId() {
        throw new NotYetImplementedException();
    }

    public UserRole GetUserRole() {
        Authentication auth = getAuth();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String role = authorities.toArray()[0].toString();
        return new UserRole(role);
    }

    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
