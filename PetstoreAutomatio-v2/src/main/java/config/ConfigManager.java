package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream is = ConfigManager.class
                .getClassLoader()
                .getResourceAsStream("config/qa.properties")) {

            if (is == null) {
                throw new RuntimeException("qa.properties not found in classpath");
            }

            properties.load(is);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    // Optional helpers ....not required...just added for future issues
    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getApiKey() {
        return get("api.key");
    }
}
