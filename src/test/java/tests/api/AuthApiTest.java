package tests.api;

import dto.response.GenerateTokenResponse;
import dto.response.RegisterResponse;
import org.junit.jupiter.api.Test;
import services.AuthService;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AuthApiTest {

    @Test
    void loginViaApi_shouldReturnToken() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        // register user
        RegisterResponse registerResponse = AuthService.register(userName, userPassword);
        assertEquals(userName, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());

        // login user
        GenerateTokenResponse response = AuthService.login(userName, userPassword);

        assertNotNull(response.getToken(), "Token is not null");
        assertEquals("User authorized successfully.", response.getResult());
        assertEquals("Success", response.getStatus());
    }
}
