package utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {
    private static final Faker faker = new Faker();

    public static String randomUserName() {
        return "user_" + faker.name().username() + faker.number().randomNumber(4, false);
    }

    public static String randomPassword() {
        return faker.internet().password(8, 16, true, true, true);
    }

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }
}
