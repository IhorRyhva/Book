package com.mtFirstProject.Booking.book.Security;


import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class JwtConvert implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, Object> realms = jwt.getClaim("realm_access");

        if(realms == null || !realms.containsKey("roles")){
            List<String> roles = new ArrayList<>();
            roles.add("just-user");
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        List<String> roles = (List<String>) realms.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
