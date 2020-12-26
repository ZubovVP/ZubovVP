package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
public class StoreXMLTest {
    private Config config = new Config();
    private Properties values = new Properties();

    @Before
    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("store.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private String get(String key) {
        return new File("").getAbsolutePath() +
                this.values.getProperty(key);
    }

    @Test
    public void saveTest() throws IOException {
        try {
            //Connect to DB
            StoreSQL storeSQL = new StoreSQL(this.config);
            //Create entries
            storeSQL.generate(1);
            //Read entries from DB
            List<StoreXML.Field> listFields = storeSQL.getAllEntries();
            //Convert object XML
            StoreXML storeXML = new StoreXML(new File(get("Result_XML")));
            storeXML.save(listFields);
            String result1 = new String(Files.readAllBytes(Paths.get(get("Result_XML"))));
            String expectation1 = String.format("%s\n%s\n%s\n%s\n%s\n%s\n", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<entries>", "    <entry>", "        <field>1</field>", "    </entry>", "</entries>");
            assertThat(result1, is(expectation1));
            storeSQL.deleteAllEntryes();
            storeSQL.generate(5);
            listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            String result2 = new String(Files.readAllBytes(Paths.get(get("Result_XML"))));
            String expectation2 = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<entries>", "    <entry>", "        <field>1</field>", "    </entry>", "    <entry>", "        <field>2</field>", "    </entry>", "    <entry>", "        <field>3</field>", "    </entry>", "    <entry>", "        <field>4</field>", "    </entry>", "    <entry>", "        <field>5</field>", "    </entry>", "</entries>");
            assertThat(result2, is(expectation2));
            storeSQL.deleteAllEntryes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}