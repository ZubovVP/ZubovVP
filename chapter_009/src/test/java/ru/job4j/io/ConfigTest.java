package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private File folder;
    private File file;
    private Config config;

    @Before
    public void start() {
        this.folder = new File(System.getProperty("user.dir") + "/src/main/java/ru/job4j/io/TestConfig");
        folder.mkdir();
        this.file = new File(System.getProperty("user.dir") + "/src/main/java/ru/job4j/io/TestConfig/app.properties");
        StringBuilder sb = new StringBuilder();
        sb.append("## PostgreSQL").append("\n");
        sb.append("\n");
        sb.append("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect").append("\n");
        sb.append("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio").append("\n");
        sb.append("hibernate.connection.driver_class=org.postgresql.Driver").append("\n");
        sb.append("hibernate.connection.username=postgres").append("\n");
        sb.append("hibernate.connection.password=password").append("\n");
        try (FileWriter writer = new FileWriter(this.file.getPath(), false)) {
            this.file.createNewFile();
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.config = new Config(this.file.getPath());
    }

    @After
    public void finish() {
        this.file.delete();
        this.folder.delete();
    }

    /**
     * Check value after load file.
     */
    @Test
    public void loadFileInTheConfig() {
        String result = this.config.value("hibernate.dialect");
        assertNull(result);
        this.config.load();
        result = this.config.value("hibernate.dialect");
        assertNotNull(result);
    }

    /**
     * Check all values in the file.
     */
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