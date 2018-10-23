package ru.job4j.store;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.concurrent.TimeoutException;

import static ru.job4j.store.StoreSQL.TIME_START;

/**
 * Created by ZubovVP on 20.10.2018
 * zubovvp@yadndex.ru
 */
public class SAXPars extends DefaultHandler {
    private static final String ENTRY_TAG = "entry";
    private static final String FIELD = "field";
    private int count;


    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(ENTRY_TAG)) {
            this.count = this.count + Integer.valueOf(attributes.getValue(FIELD));
        }
        if (System.currentTimeMillis() - TIME_START >= (300000)) {
            try {
                throw new TimeoutException("Time is over");
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(this.count);
    }
}
