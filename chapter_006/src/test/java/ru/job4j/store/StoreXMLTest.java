package ru.job4j.store;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class StoreXMLTest {
    private Config config = new Config();

    @Test
    public void saveTest() throws IOException {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            storeSQL.generate(1);
            List<StoreXML.Field> listFields = storeSQL.getAllEntries();
            StoreXML storeXML = new StoreXML(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"));

            storeXML.save(listFields);
            String result1 = new String(Files.readAllBytes(Paths.get("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml")));
            String expectation1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<entries>\n" + "    <entry>\n" + "        <field>1</field>\n" + "    </entry>\n" + "</entries>\n";
            assertThat(result1, is(expectation1));

            storeSQL.generate(5);
            listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            String result2 = new String(Files.readAllBytes(Paths.get("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml")));
            String expectation2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + "<entries>\n" + "    <entry>\n" + "        <field>1</field>\n" + "    </entry>\n" + "    <entry>\n" + "        <field>2</field>\n" + "    </entry>\n" + "    <entry>\n" + "        <field>3</field>\n" + "    </entry>\n" + "    <entry>\n" + "        <field>4</field>\n" + "    </entry>\n" + "    <entry>\n" + "        <field>5</field>\n" + "    </entry>\n" + "</entries>\n";
            assertThat(result2, is(expectation2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}