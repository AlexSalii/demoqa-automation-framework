package tests.api;

import dto.response.RegisterResponse;
import org.junit.jupiter.api.Test;
import services.AuthService;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserApiTest {

    @Test
    void registerUser_shouldSucceed() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        RegisterResponse registerResponse = AuthService.register(userName, userPassword);

        assertEquals(userName, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());
        assertTrue(registerResponse.getBooks().isEmpty());
    }
}
