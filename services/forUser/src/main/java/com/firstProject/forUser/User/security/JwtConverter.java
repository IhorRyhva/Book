package com.firstProject.forUser.User.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.*;
import java.util.stream.Collectors;

public class JwtConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        System.out.println("JWT claims: " + jwt.getClaims());

        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if(realmAccess == null || !realmAccess.containsKey("roles")){
            List<String> roles = new ArrayList<>();
            roles.add("just-user");
            return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        }

        List<String> roles = (List<String>) realmAccess.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).peek(System.out::println).collect(Collectors.toList());
    }

}
