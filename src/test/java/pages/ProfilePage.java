package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static utils.ConfigReader.getBaseUrl;

public class ProfilePage {
    private final Page page;
    private final Locator usernameLabel;

    public ProfilePage(Page page) {
        this.page = page;
        this.usernameLabel = page.locator("#userName-value");
    }

    public boolean isCurrentPage(){
        page.waitForURL(getBaseUrl() + "/profile", new Page.WaitForURLOptions().setTimeout(5000));
        return page.url().equals(getBaseUrl() + "/profile");
    }

    private void sleap() {
    }

    public String getUsername() {
        return usernameLabel.textContent();
    }
}
