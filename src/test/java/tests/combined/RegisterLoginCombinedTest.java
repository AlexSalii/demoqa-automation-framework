package tests.combined;

import base.BaseTest;
import dto.response.RegisterResponse;
import flows.UserFlow;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import services.AuthService;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterLoginCombinedTest extends BaseTest {

    @Test
    public void registerViaApi_thenLoginViaUi_shouldSucceed() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        // Register user via API
        RegisterResponse registerResponse = AuthService.register(userName, userPassword);
        assertEquals(userName, registerResponse.getUsername());
        assertNotNull(registerResponse.getUserId());
        test.pass("User was successfully created via API");

        // Open login page via UI
        LoginPage loginPage = new LoginPage(page).open();

        ProfilePage profilePage = loginPage.login(userName, userPassword);

        assertTrue(profilePage.isCurrentPage());
        assertEquals(userName, profilePage.getUsername());
        test.pass("Login successful, profile page is visible");
    }

    @Test
    public void loginViaUi_afterApiRegistration_shouldSucceed() {
        // Register user via API
        user.set(UserFlow.registerUser());
        test.pass("User was successfully created via API");

        // Open login page via UI
        LoginPage loginPage = new LoginPage(page).open();

        ProfilePage profilePage = loginPage.login(user.get().getUserName(), user.get().getPassword());

        assertTrue(profilePage.isCurrentPage());
        assertEquals(user.get().getUserName(), profilePage.getUsername());
        test.pass("Login successful, profile page is visible");
    }
}
