package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static utils.ConfigReader.getBaseUrl;

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
        page.navigate(getBaseUrl() + "/login");

        return this;
    }

    public LoginPage login(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();

        return this;
    }

    public boolean isOnProfilePage() {
        return page.url().equals(getBaseUrl() + "/profile");
    }

    public String getErrorMessage() {
        return errorMessage.textContent();
    }
}
