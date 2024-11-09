package br.com.er.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.smallrye.jwt.build.Jwt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LivroControllerTest {

    private static String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8085;
        
        token = "Bearer " + generateValidToken();
    }

    private static String generateValidToken() {
        return Jwt.issuer("http://localhost:8085")
                  .subject("user@example.com")
                  .groups("User") // ou "Admin"
                  .expiresAt(System.currentTimeMillis() + 3600) // Expira em 1 hora
                  .sign();
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

    // @Test
    // public void shouldReturn200WhenGettingBookById() {
    //     Long livroId = 1L;

    //     given()
    //             .header("Authorization", token)
    //             .contentType(ContentType.JSON)
    //             .when()
    //             .get("/livro/" + livroId)
    //             .then()
    //             .statusCode(200)
    //             .body("id", equalTo(livroId.intValue()));
    // }

    // @Test
    // public void shouldReturn200WhenUpdatingBook() {
    //     Long livroId = 1L;
    //     String requestBody = "{" +
    //             "\"titulo\": \"Livro Atualizado\"," +
    //             "\"autor\": \"Autor Atualizado\"," +
    //             "\"dataLancamento\": \"2024-11-09\"}";

    //     given()
    //             .header("Authorization", token)
    //             .contentType(ContentType.JSON)
    //             .body(requestBody)
    //             .when()
    //             .put("/livro/" + livroId)
    //             .then()
    //             .statusCode(200)
    //             .body("titulo", equalTo("Livro Atualizado"));
    // }

    // @Test
    // public void shouldReturn204WhenDeletingBook() {
    //     Long livroId = 1L;

    //     given()
    //             .header("Authorization", token)
    //             .contentType(ContentType.JSON)
    //             .when()
    //             .delete("/livro/" + livroId)
    //             .then()
    //             .statusCode(204);
    // }

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
}
