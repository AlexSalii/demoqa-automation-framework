package services;

import dto.request.GenerateTokenRequest;
import dto.request.RegisterRequest;
import dto.response.GenerateTokenResponse;
import dto.response.RegisterResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static utils.ConfigReader.getBaseUrl;

@Slf4j
public class AuthService {

    public static RegisterResponse register(String username, String password) {
        log.info("Sending login request for user: {}", username);

        return given()
                    .log().all()
                    .baseUri(getBaseUrl())
                    .basePath("/Account/v1/User")
                    .contentType(JSON)
                    .body(new RegisterRequest(username, password))
                .when()
                    .post()
                .then()
                    .log().all()
                    .statusCode(201)
                    .extract()
                    .as(RegisterResponse.class);
    }

    public static GenerateTokenResponse login(String username, String password) {
        log.info("Sending registration request for user: {}", username);

        return given()
                    .baseUri(getBaseUrl())
                    .basePath("/Account/v1/GenerateToken")
                    .contentType(JSON)
                    .body(new GenerateTokenRequest(username, password))
                .when()
                    .post()
                .then()
                    .statusCode(200)
                    .extract()
                    .as(GenerateTokenResponse.class);
    }

    public static void deleteUser(String userId, String token){
         given()
                 .baseUri(getBaseUrl())
                 .basePath("/Account/v1/User/" + userId)
                 .header("Authorization", "Bearer " + token)
         .when()
                 .delete()
         .then()
                 .statusCode(204);
    }
}
