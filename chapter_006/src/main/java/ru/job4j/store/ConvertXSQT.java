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
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(
                new StreamSource(scheme));
        StreamSource ss = new StreamSource(source);
        StreamResult sr = new StreamResult(dest);
        transformer.transform(ss, sr);
    }
}
