package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("test.properties")) {
            if(input == null) {
                throw new RuntimeException("test.properties file not found in resources");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test.properties file", e);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }
}
