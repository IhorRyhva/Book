package com.mtFirstProject.Booking.book.Security;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ExtractUserEmail {
    private String email;

    public void extractEmail(Jwt token){
        email = token.getClaims().get("email").toString();
    }

    public String getEmail() {
        return email;
    }
}
