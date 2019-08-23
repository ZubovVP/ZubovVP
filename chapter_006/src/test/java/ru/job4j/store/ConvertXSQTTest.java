package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class ConvertXSQTTest {
    private Config config = new Config();
    private Properties values = new Properties();

    @Before
    public void loadOutput() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("store.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private String get(String key) {
        return this.values.getProperty(key);
    }

    @Test
    public void convertTest() {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            StoreXML storeXML = new StoreXML(new File(get("Result_XML")));

            storeSQL.generate(1);
            List<StoreXML.Field> listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            File source = new File(get("Result_XML"));
            File scheme = new File(get("Scheme"));
            File dist = new File(get("Result_XML_XSLT"));
            convertXSQT.convert(source, dist, scheme);
            String result1 = new String(Files.readAllBytes(Paths.get(get("Result_XML_XSLT"))));
            String expectation1 = String.format("%s\r\n%s\r\n%s\r\n", "<entries>", "    <entry field=\"1\"/>", "</entries>");
            assertThat(result1, is(expectation1));

            storeSQL.generate(5);
            listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            source = new File(get("Result_XML"));
            scheme = new File(get("Scheme"));
            dist = new File(get("Result_XML_XSLT"));
            convertXSQT.convert(source, dist, scheme);
            String result2 = new String(Files.readAllBytes(Paths.get(get("Result_XML_XSLT"))));
            String expectation2 = String.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n", "<entries>", "    <entry field=\"1\"/>", "    <entry field=\"2\"/>", "    <entry field=\"3\"/>", "    <entry field=\"4\"/>", "    <entry field=\"5\"/>", "</entries>");
            assertThat(result2, is(expectation2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}