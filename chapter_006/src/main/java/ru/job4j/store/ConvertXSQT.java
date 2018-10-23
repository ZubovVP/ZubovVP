package ru.job4j.store;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;


/**
 * Created by ZubovVP on 17.10.2018
 * zubovvp@yadndex.ru
 */
public class ConvertXSQT {

    /**
     * Convert XML use XSTL.
     *
     * @param source - File source.
     * @param scheme - File scheme.
     * @throws TransformerException - TransformerException.
     * @throws IOException          - IOException.
     */
    public void convert(File source, File scheme) throws TransformerException, IOException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(scheme));
        StreamSource ss = new StreamSource(source);
        StreamResult sr = new StreamResult(new File("C:\\projects\\ZubovVP\\chapter_006\\src\\main\\java\\ru\\job4j\\store\\files\\Result_XML_XSLT.xml"));
        transformer.transform(ss, sr);
    }

}
