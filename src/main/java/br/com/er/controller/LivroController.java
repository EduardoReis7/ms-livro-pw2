package br.com.er.controller;

import br.com.er.controller.converter.LivroConverter;
import br.com.er.model.dto.LivroInDto;
import br.com.er.service.LivroService;
import br.com.er.service.TokenService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/livro")
public class LivroController {
    
    private final LivroService livroService;
    private final TokenService tokenService;

    public LivroController(LivroService livroService, TokenService tokenService) {
        this.livroService = livroService;
        this.tokenService = tokenService;
    }

    @POST
    @Transactional
    @RolesAllowed({"User", "Admin"})
    public Response salvar(@HeaderParam("Authorization") String authHeader, LivroInDto dto) {
        return Response.ok(LivroConverter.convertEntityToOutDto(this.livroService.salvar(LivroConverter.convertInDtoToEntity(dto)))).build();

    }

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response buscarTodos() {
        return Response.ok(LivroConverter.convertEntityListToOutDtoList(this.livroService.buscarTodos())).build();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@HeaderParam("Authorization") String authHeader, @PathParam("id") Long id) {
        return Response.ok(LivroConverter.convertEntityToOutDto(this.livroService.buscar(id))).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"User", "Admin"})
    public Response editar(@HeaderParam("Authorization") String authHeader, @PathParam("id") Long id, LivroInDto dto) {
        return Response.ok(LivroConverter.convertEntityToOutDto(this.livroService.editar(id, LivroConverter.convertInDtoToEntity(dto)))).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @RolesAllowed({"User", "Admin"})
    public Response excluir(@HeaderParam("Authorization") String authHeader, @PathParam("id") Long id) {
        this.livroService.excluir(id);
        return Response.noContent().build();
    }
}
