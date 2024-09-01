package my.code.admin.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;


import my.code.admin.dtos.AuthenticatedUserDto;
import my.code.admin.entities.User;
import my.code.admin.services.AuthService;
import my.code.admin.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticatedUserDto authAttempt(String username, String password) {
        var user = userService.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password!");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        return successfulAuth(authenticationManager.authenticate(authenticationToken));
    }


    private AuthenticatedUserDto successfulAuth(Authentication authentication) {
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        User myUser = userService.findByUsername(user.getUsername());

        AuthenticatedUserDto authenticatedUser = new AuthenticatedUserDto();
        authenticatedUser.setUserId(myUser.getId());
        authenticatedUser.setUsername(myUser.getUsername());

        Algorithm algorithm = Algorithm.HMAC256("secretPhrase".getBytes());

        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority :: getAuthority)
                        .toList())
                .sign(algorithm);

        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30))
                .sign(algorithm);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);

        authenticatedUser.setTokens(tokens);

        return authenticatedUser;
    }
}
