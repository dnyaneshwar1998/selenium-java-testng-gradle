package com.example.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = loadProperties();

    private ConfigManager() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new IllegalStateException("Unable to find " + CONFIG_FILE + " in classpath.");
            }
            properties.load(input);
        } catch (IOException exception) {
            throw new IllegalStateException("Unable to load configuration file.", exception);
        }
        return properties;
    }

    public static String get(String key) {
        String systemOverride = System.getProperty(key);
        return systemOverride != null ? systemOverride : PROPERTIES.getProperty(key);
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Integer.parseInt(value.trim());
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value.trim());
    }
}
