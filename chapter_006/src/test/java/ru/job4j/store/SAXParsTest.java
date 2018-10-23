package ru.job4j.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class SAXParsTest {
    private Config config = new Config();
    private final PrintStream stdout = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));

    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void testPars() {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            storeSQL.generate(1);
            List<StoreXML.Field> result = storeSQL.getAllEntries();
            StoreXML storeXML = new StoreXML(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"));
            storeXML.save(result);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            convertXSQT.convert(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"), new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\Scheme.xml"));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            SAXPars saxPars = new SAXPars();
            xmlReader.setContentHandler(saxPars);
            xmlReader.parse("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML_XSLT.xml");
            assertThat(new String(out.toByteArray()), is("1\r\n"));

            out = new ByteArrayOutputStream();
            storeSQL.generate(3);
            result = storeSQL.getAllEntries();
            storeXML = new StoreXML(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"));
            storeXML.save(result);
            convertXSQT = new ConvertXSQT();
            convertXSQT.convert(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML.xml"), new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\Scheme.xml"));
            spf = SAXParserFactory.newInstance();
            saxParser = spf.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            saxPars = new SAXPars();
            xmlReader.setContentHandler(saxPars);
            System.setOut(new PrintStream(this.out));
            xmlReader.parse("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML_XSLT.xml");
            assertThat(new String(out.toByteArray()), is("6\r\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}