package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProfilePage;
import utils.FakeDataGenerator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @Tag("skipCleanup")
    void loginWithValidCredentials_shouldNavigateToProfilePage() {
        String userName = "testValidUser";
        String userPassword = "testValidPassword1@";

        LoginPage loginPage = new LoginPage(page).open();

        assertTrue(loginPage.isCurrentPage(), "Expected to be on login page after open()");

        ProfilePage profilePage = loginPage.login(userName, userPassword);

        assertTrue(
                profilePage.isCurrentPage(),
                "Expected to be on profile page after successful login"
        );
        assertEquals(userName, profilePage.getUsername(), "Displayed username should match logged-in user");

        test.pass("Login successful, profile page is visible");
    }

    @Test
    void loginWithInvalidCredentials_showsError() {
        String userName = FakeDataGenerator.randomUserName();
        String userPassword = FakeDataGenerator.randomPassword();

        LoginPage loginPage = new LoginPage(page).open().attemptLogin(userName, userPassword);

        String errorText = loginPage.getErrorMessage();
        assertTrue(errorText.contains("Invalid"), "Expected error message about invalid credentials");

        test.pass("Error messages displayed correctly: " + errorText);
    }
}
