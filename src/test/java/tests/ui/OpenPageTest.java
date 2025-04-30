package tests.ui;

import base.BaseTest;
import org.junit.jupiter.api.Test;

public class OpenPageTest extends BaseTest {
    @Test
    void openBookstoreHomePage() {
        page.navigate("https://demoqa.com/books");
        test.pass("Opened Bookstore page");
    }
}
