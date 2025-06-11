package com.firstProject.forUser.User.security;


import com.firstProject.forUser.User.User;
import com.firstProject.forUser.User.UserMapping;
import com.firstProject.forUser.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSynchronizer {
    private final UserRepository userRepository;
    private final UserMapping mapper;

    public void synchronizedWitIdp(Jwt token) {
    getUserFromJwt(token).ifPresent(
            userEmail -> {
                Optional<User> optionalUser = userRepository.findByEmail(userEmail);
                if(optionalUser.isEmpty()) {
                    User user = mapper.fromTokenClaims(token.getClaims());
                    userRepository.save(user);
                }
            }
    );

    }

    private Optional<String> getUserFromJwt(Jwt jwt){
        Map<String, Object> claims = jwt.getClaims();

        if(claims.containsKey("email")){
            return Optional.of(claims.get("email").toString());
        }
        return Optional.empty();
    }
}
