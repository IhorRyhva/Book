package com.mtFirstProject.Booking.book.Security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeyCloakAuthenticationToken implements Converter<Jwt, AbstractAuthenticationToken> {


    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        return new JwtAuthenticationToken(
                source, Stream.concat(
                        new JwtGrantedAuthoritiesConverter().convert(source).stream(),
                        jwtGetRoles(source).stream()).toList()
        );
    }

    private Collection<? extends GrantedAuthority> jwtGetRoles(Jwt source) {
        var resources = new HashMap<>(source.getClaim("resource_access"));

        var client = (Map<String, List<String >>) resources.get("krasavchik");

        var roles = client.get("roles");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }


}
