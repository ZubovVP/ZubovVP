package ru.job4j.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class SAXParsTest {
    private Config config = new Config();
    private Properties values = new Properties();
    private final PrintStream stdout = System.out;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("store.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    private String get(String key) {
        return this.values.getProperty(key);
    }

    @Test
    public void testPars() {
        try {
            StoreSQL storeSQL = new StoreSQL(this.config);
            storeSQL.generate(1);
            List<StoreXML.Field> result = storeSQL.getAllEntries();
            StoreXML storeXML = new StoreXML(new File(get("Result_XML")));
            storeXML.save(result);
            ConvertXSQT convertXSQT = new ConvertXSQT();
            convertXSQT.convert(new File(get("Result_XML")), new File(get("Result_XML_XSLT")), new File(get("Scheme")));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser saxParser = spf.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            SAXPars saxPars = new SAXPars();
            xmlReader.setContentHandler(saxPars);
            xmlReader.parse(get("Result_XML_XSLT"));
            assertThat(new String(out.toByteArray()), is("1\r\n"));

            out = new ByteArrayOutputStream();
            storeSQL.generate(3);
            result = storeSQL.getAllEntries();
            storeXML = new StoreXML(new File(get("Result_XML")));
            storeXML.save(result);
            convertXSQT = new ConvertXSQT();
            convertXSQT.convert(new File(get("Result_XML")), new File(get("Result_XML_XSLT")), new File(get("Scheme")));
            spf = SAXParserFactory.newInstance();
            saxParser = spf.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            saxPars = new SAXPars();
            xmlReader.setContentHandler(saxPars);
            System.setOut(new PrintStream(this.out));
            xmlReader.parse(get("Result_XML_XSLT"));
            assertThat(new String(out.toByteArray()), is("6\r\n"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}