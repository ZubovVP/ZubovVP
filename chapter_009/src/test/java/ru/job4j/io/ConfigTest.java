package ru.job4j.io;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 12.08.2019.
 */
public class ConfigTest {
    private Config config;

    @Before
    public void start() {
        this.config = new Config("C:\\projects\\ZubovVP\\chapter_009\\src\\main\\java\\ru\\job4j\\app.properties");
    }

    @Test
    public void loadFileInTheConfig() {
        String result = this.config.value("hibernate.dialect");
        assertNull(result);
        this.config.load();
        result = this.config.value("hibernate.dialect");
        assertNotNull(result);
    }

    @Test
    public void checkValuesInTheConfig() {
        this.config.load();
        assertThat(this.config.value("hibernate.dialect"), is("org.hibernate.dialect.PostgreSQLDialect"));
        assertThat(this.config.value("hibernate.connection.url"), is("jdbc:postgresql://127.0.0.1:5432/trackstudio"));
        assertThat(this.config.value("hibernate.connection.driver_class"), is("org.postgresql.Driver"));
        assertThat(this.config.value("hibernate.connection.username"), is("postgres"));
        assertThat(this.config.value("hibernate.connection.password"), is("password"));
        assertNull(this.config.value("## PostgreSQL"));
        assertNull(this.config.value("##"));
    }
}