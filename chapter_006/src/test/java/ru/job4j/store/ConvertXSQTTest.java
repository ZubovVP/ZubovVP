package ru.job4j.store;

import org.junit.Test;

import javax.xml.transform.TransformerException;
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
public class ConvertXSQTTest {
    private Config config = new Config();

    @Test
    public void convertTest() throws TransformerException, IOException {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            StoreXML storeXML = new StoreXML(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"));
            ConvertXSQT convertXSQT = new ConvertXSQT();

            storeSQL.generate(1);
            List<StoreXML.Field> listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            File source = new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml");
            File scheme = new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\Scheme.xml");
            convertXSQT.convert(source, scheme);
            String result1 = new String(Files.readAllBytes(Paths.get("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML_XSLT.xml")));
            String expectation1 = String.format("%s\r\n%s\r\n%s\r\n", "<entries>", "<entry field=\"1\"/>", "</entries>");
            assertThat(result1, is(expectation1));

            storeSQL.generate(5);
            listFields = storeSQL.getAllEntries();
            storeXML.save(listFields);
            source = new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml");
            scheme = new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\Scheme.xml");
            convertXSQT.convert(source, scheme);
            String result2 = new String(Files.readAllBytes(Paths.get("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML_XSLT.xml")));
            String expectation2 = String.format("%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n", "<entries>", "<entry field=\"1\"/>", "<entry field=\"2\"/>", "<entry field=\"3\"/>", "<entry field=\"4\"/>", "<entry field=\"5\"/>", "</entries>");
            assertThat(result2, is(expectation2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}