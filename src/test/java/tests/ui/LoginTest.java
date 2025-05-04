package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    // NOTE: For this test, the user was created manually on the login page beforehand.
    // Later this can be replaced with API-based user registration for better automation.
    @Test
    void loginWithValidCredentials_shouldNavigateToProfilePage() {
        LoginPage loginPage = new LoginPage(page).open();

        loginPage.login("testValidUser", "testValidPassword1@");

        assertTrue(
                loginPage.isOnProfilePage(),
                "Expected to be on profile page after successful login"
        );

        test.pass("Login successful, profile page is visible");
    }

    @Test
    void loginWithInvalidCredentials_showsError() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        LoginPage loginPage = new LoginPage(page).open().login(userName, userPassword);

        String errorText = loginPage.getErrorMessage();
        assertTrue(errorText.contains("Invalid"), "Expected error message about invalid credentials");

        test.pass("Error messages displayed correctly: " + errorText);
    }
}
