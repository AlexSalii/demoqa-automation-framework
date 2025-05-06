package utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
    private static final Faker faker = new Faker();

    public static String randomUserName() {
        return "user_" + faker.name().username() + faker.number().randomNumber(4, false);
    }

    /**
     * Generates a password that satisfies current DemoQA password requirements:
     * - at least one uppercase, one lowercase, one digit, one special character
     * - minimum 8 characters
     *
     * NOTE: This is a simplified version for quick use.
     * TODO: Replace with a more flexible and random password generator if needed.
     */
    public static String randomPassword() {
        return "A1b@" + faker.letterify("????");
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }
}
