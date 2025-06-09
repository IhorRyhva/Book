package com.firstProject.Hotel.Security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class JwtConvert implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> claims = source.getClaim("realm_access");

        if (claims == null || !claims.containsKey("roles")) {
            List<String> role = new ArrayList<>();
            role.add("just-user");
            return role.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }
        Set<String> roles = (Set<String>) claims.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
