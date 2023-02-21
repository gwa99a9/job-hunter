package com.gsd.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadProperties(String fileName) throws IOException {
        Properties properties = new Properties();
        InputStream stream = null;
        try {
            stream = PropertiesLoader.class.getClassLoader()
                    .getResourceAsStream(fileName + ".properties");
            if (stream == null) {
                throw new IOException("Failed to load properties file: " + fileName + ".properties");
            }
            properties.load(stream);
        } catch (Exception e) {
            throw new IOException("Failed to load properties file: " + fileName + ".properties", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
        return properties;
    }
}