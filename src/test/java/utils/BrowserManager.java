package utils;

import com.microsoft.playwright.*;

public class BrowserManager {

    protected static Playwright playwright;
    protected static Browser browser;

    public static BrowserContext createBrowserContext() {
        if (playwright == null) {
            playwright = Playwright.create();
        }
        if (browser == null) {
            String browserName = ConfigReader.getString("browser.name");
            Boolean headless = ConfigReader.getBoolean("browser.headless");

            BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                    .setHeadless(headless);

            browser = switch (browserName.toLowerCase()) {
                case "chromium" -> playwright.chromium().launch(options);
                case "firefox" -> playwright.firefox().launch(options);
                case "webkit" -> playwright.webkit().launch(options);
                default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
            };
        }
        return browser.newContext();
    }

    public static void quit() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
