package br.com.er.service;

import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.JsonWebToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class TokenService {

    @Inject
    JWTParser jwtParser;
    
    // Você pode injetar o JsonWebToken, mas aqui vamos criar uma nova instância do Jwt
    public boolean isValid(String token) {
        try {
            JsonWebToken jwt = jwtParser.parse(token);
            return jwt.getSubject() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<String> getUsername(String token) {
        try {
            JsonWebToken jwt = jwtParser.parse(token);
            return Optional.ofNullable(jwt.getName());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
