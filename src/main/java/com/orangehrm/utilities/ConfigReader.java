package com.orangehrm.utilities;

import com.orangehrm.constants.FrameworkConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    static {

        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(FrameworkConstants.CONFIG_FILE)) {

            if (inputStream == null) {
                throw new RuntimeException("Config file not found: " + FrameworkConstants.CONFIG_FILE);
            }

            properties.load(inputStream);

        } catch (IOException e) {

            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    private ConfigReader() {
    }

    public static String getProperty(String key) {

        String value = properties.getProperty(key);

        if (value == null) {
            throw new IllegalArgumentException("Property not found: " + key);
        }

        return value.trim();
    }
}