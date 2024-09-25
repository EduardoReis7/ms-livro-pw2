package br.com.er.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {

        if (e instanceof NaoEncontradoException) {
            return Response.status(Response.Status.NOT_FOUND).entity("Erro: não foi possível encontrar o objeto solicitado.").build();
        }
        
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno: Não foi possível processar sua solicitação!").build();
    }

    
}