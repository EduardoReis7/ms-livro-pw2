package br.com.er.controller;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.Claims;

import br.com.er.model.dto.LivroLoginDto;
import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import java.util.HashSet;
import java.util.Arrays;

@Path("/login")
public class LoginController {

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private String ISSUER;
    
    @POST
    @PermitAll
    public Response generateJwt(LivroLoginDto dto) {
        String jwt = Jwt.issuer(ISSUER)
                            .upn(dto.getLogin())
                            .groups(new HashSet<>(Arrays.asList("User", "Admin")))
                            .expiresAt(System.currentTimeMillis() / 1000 + 3600)
                            .sign();
        return Response.ok(jwt).build();
    }
}
