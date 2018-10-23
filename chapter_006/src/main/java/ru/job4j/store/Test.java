package ru.job4j.store;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by ZubovVP on 21.10.2018
 * zubovvp@yadndex.ru
 */
public class Test {
    public static void main(String[] args) throws TransformerException, IOException, SAXException, ParserConfigurationException {
        StoreSQL storeSQL = new StoreSQL(new ru.job4j.store.Config());
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
    }
}
