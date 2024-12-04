package br.com.er.controller;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import br.com.er.model.Livro;
import br.com.er.repository.LivroRepository;
import br.com.er.utils.TestContainersConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.time.LocalDate;

@QuarkusTest
@QuarkusTestResource(TestContainersConfig.class)
public class LivroControllerTest {

    @Inject
    private LivroRepository livroRepository;

    private static String token;
    private Long livroId;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        
        token = "Bearer " + generateValidToken();
    }

    @BeforeEach
    @Transactional
    public void setupData() {
        Livro livro = Livro.builder()
            .autor("Autor teste")
            .titulo("Titulo teste")
            .dataLancamento(LocalDate.of(1950, 02,14))
            .build();
        livroRepository.persist(livro);
        livroId = livro.getId();
    }


    @Test
    public void shouldReturn200WhenSavingLivro() {
        String requestBody = "{" +
                "\"titulo\": \"Livro Teste\"," +
                "\"autor\": \"Autor Teste\"," +
                "\"dataLancamento\": \"2024-11-09\"}";

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/livro")
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Livro Teste"));
    }

    @Test
    public void shouldReturn200WhenGettingAllBooks() {
        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .get("/livro")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    public void shouldReturn200WhenGettingBookById() {

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .get("/livro/" + livroId)
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Titulo teste"));
    }

    @Test
    public void shouldReturn200WhenUpdatingBook() {
        String requestBody = "{" +
                "\"titulo\": \"Livro Atualizado\"," +
                "\"autor\": \"Autor Atualizado\"," +
                "\"dataLancamento\": \"2024-11-09\"}";

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/livro/" + livroId)
                .then()
                .statusCode(200)
                .body("titulo", equalTo("Livro Atualizado"));
    }

    @Test
    public void shouldReturn204WhenDeletingBook() {

        given()
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .delete("/livro/" + livroId)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldReturn401WhenUnauthorized() {
        String requestBody = "{" +
                "\"titulo\": \"Livro Teste\"," +
                "\"autor\": \"Autor Teste\"," +
                "\"dataLancamento\": \"2024-11-09\"}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/livro")
                .then()
                .statusCode(401);
    }

    private static String generateValidToken() {
        return Jwt.issuer("http://localhost:8085")
                  .subject("user@example.com")
                  .groups("User") // ou "Admin"
                  .expiresAt(System.currentTimeMillis() + 3600) // Expira em 1 hora
                  .sign();
    }
}
