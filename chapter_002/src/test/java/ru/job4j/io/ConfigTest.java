package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/resources/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
        assertThat(
                config.value("hibernate.connection.username"),
                is("postgres")
        );
        assertThat(
                config.value("#Line"),
                is(nullValue())
        );
        assertThat(
                config.value(null),
                is(nullValue())
        );
    }
}
