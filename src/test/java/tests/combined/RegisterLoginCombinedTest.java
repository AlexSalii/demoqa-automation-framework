package tests.combined;

import base.BaseTest;
import context.UserContext;
import dto.response.RegisterResponse;
import flows.UserFlow;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import services.AuthService;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class RegisterLoginCombinedTest extends BaseTest {

    @Test
    public void registerViaApi_thenLoginViaUi_shouldSucceed() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        // Register user via API
        test.info("Register user via API");
        RegisterResponse registerResponse = AuthService.register(userName, userPassword);
        assertEquals(userName, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());

        test.info("User created via API");

        // Open login page via UI
        LoginPage loginPage = new LoginPage(page).open();

        ProfilePage profilePage = loginPage.login(userName, userPassword);
        test.info("Login via UI");

        assertTrue(profilePage.isCurrentPage(), "Expected to be on profile page");
        assertEquals(userName, profilePage.getUsername(), "Username should match on profile page");

        test.info("Login successful, profile page is visible");
    }

    @Test
    public void loginViaUi_afterApiRegistration_shouldSucceed() {
        // Register user via API
        UserContext newUser = UserFlow.registerUser();

        user.set(newUser);
        log.info("User: {} was successfully created via API", newUser);

        // Open login page via UI
        LoginPage loginPage = new LoginPage(page).open();

        ProfilePage profilePage = loginPage.login(user.get().getUserName(), user.get().getPassword());
        assertTrue(profilePage.isCurrentPage(), "Expected to be on profile page");
        assertEquals(user.get().getUserName(), profilePage.getUsername());

        log.info("Login successful, profile page is visible");
    }
}
