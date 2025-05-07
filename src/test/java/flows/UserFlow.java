package flows;

import context.UserContext;
import dto.response.GenerateTokenResponse;
import dto.response.RegisterResponse;
import services.AuthService;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserFlow {

    public static UserContext registerUser() {
        String username = FakeDataGenerator.randomUserName();
        String password = FakeDataGenerator.randomPassword();

        RegisterResponse registerResponse = AuthService.register(username, password);
        assertEquals(username, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());

        return new UserContext(username, password, registerResponse.getUserId(), null);
    }

    public static UserContext loginUser(UserContext user) {
        GenerateTokenResponse response = AuthService.login(user.getUserName(), user.getPassword());
        user.setToken(response.getToken());
        return user;
    }


}
