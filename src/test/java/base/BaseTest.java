package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import context.UserContext;
import org.junit.jupiter.api.*;

import services.AuthService;
import utils.ReportManager;

public class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    protected static ExtentReports extent;
    protected ExtentTest test;

    protected static ThreadLocal<UserContext> user = new ThreadLocal<>();


    @BeforeAll
    static void globalSetup() {
        extent = ReportManager.getInstance();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @AfterAll
    static void globalTeardown() {
        if (extent != null) extent.flush();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        context = browser.newContext();
        page = context.newPage();
        test = extent.createTest(testInfo.getDisplayName());
    }

    @AfterEach
    void teardown() {
        if (context != null) context.close();
    }

    @AfterEach
    void cleanUpUser() {
        UserContext u = user.get();
        if (u != null && u.getToken() != null && u.getUserId() != null) {
            AuthService.deleteUser(u.getUserId(), u.getToken());
        }
        user.remove();
    }
}

