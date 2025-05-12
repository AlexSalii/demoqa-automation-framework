package flows;

import context.UserContext;
import dto.response.GenerateTokenResponse;
import dto.response.RegisterResponse;
import lombok.extern.slf4j.Slf4j;
import services.AuthService;
import utils.FakeDataGenerator;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class UserFlow {

    public static UserContext registerUser() {
        String username = FakeDataGenerator.randomUserName();
        String password = FakeDataGenerator.randomPassword();

        log.info("Registering new user: {}", username);

        RegisterResponse registerResponse = AuthService.register(username, password);
        assertEquals(username, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());

        return new UserContext(username, password, registerResponse.getUserId(), null);
    }

    public static UserContext loginUser(UserContext user) {
        log.info("Logging in user: {}", user.getUserName());

        GenerateTokenResponse response = AuthService.login(user.getUserName(), user.getPassword());
        user.setToken(response.getToken());
        return user;
    }


}
