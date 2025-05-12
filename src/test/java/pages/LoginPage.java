package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;

import static utils.ConfigReader.getBaseUrl;

@Slf4j
public class LoginPage {
    private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;

        this.usernameInput = page.locator("#userName");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("#login");
        this.errorMessage = page.locator("#name");
    }

    public LoginPage open() {
        log.info("Navigating to login page");
        page.navigate(getBaseUrl() + "/login");

        return this;
    }

    public ProfilePage login(String username, String password) {
        log.info("Logging in with user: {}", username);

        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();

        return new ProfilePage(page);
    }

    public LoginPage attemptLogin(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();

        return this;
    }

    public boolean isCurrentPage() {
        return page.url().equals(getBaseUrl() + "/login");
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }
}
