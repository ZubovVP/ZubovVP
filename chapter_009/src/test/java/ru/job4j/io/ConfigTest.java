package ru.job4j.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
        this.folder = new File(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", new File("").getAbsolutePath(), File.separator, "src", File.separator, "main", File.separator, "java", File.separator, "ru", File.separator, "job4j", File.separator, "io", File.separator, "TestConfig"));
        folder.mkdir();
        this.file = new File(String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s", new File("").getAbsolutePath(), File.separator, "src", File.separator, "main", File.separator, "java", File.separator, "ru", File.separator, "job4j", File.separator, "io", File.separator, "TestConfig", File.separator, "app.properties"));
        StringBuilder sb = new StringBuilder();
        sb.append("## PostgreSQL").append("\n");
        sb.append("\n");
        sb.append("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect").append("\n");
        sb.append("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio").append("\n");
        sb.append("hibernate.connection.driver_class=org.postgresql.Driver").append("\n");
        sb.append("hibernate.connection.username=postgres").append("\n");
        sb.append("hibernate.connection.password=password").append("\n");
        try (FileWriter writer = new FileWriter(this.file.getAbsolutePath(), false)) {
            if (this.file.exists()) {
                this.file.delete();
            }
            this.file.createNewFile();
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.config = new Config(this.file.getAbsolutePath());
    }

    @After
    public void finish() {
        this.file.delete();
        this.folder.delete();
    }

    /**
     * Check value after load file.
     */
    @Ignore
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
    @Ignore
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