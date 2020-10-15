package ru.job4j.concurrent.async.in;

import org.apache.log4j.lf5.util.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SimpleProperties {

    private final Properties properties;
    public static final String PROP = "app.properties";

    public SimpleProperties() {
        properties = new Properties();
    }

    public void init() {
        try (InputStream in = new Resource(PROP).getInputStream()) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
