package ru.job4j.store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

/**
 * Created by ZubovVP on 14.10.2018
 * zubovvp@yadndex.ru
 */
public class StoreXML {
    private final File target;


    /**
     * Constructor.
     *
     * @param target - way.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Save enties from tables in XML.
     *
     * @param list - list of entries.
     */
    public void save(List<Field> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Entries(list), this.target);
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Entries.
     */
    @XmlRootElement
    public static class Entries {
        private List<Field> entries;

        /**
         * Constructor.
         */
        public Entries() {
        }

        /**
         * Constructor.
         */
        public Entries(List<Field> entries) {
            this.entries = entries;
        }

        /**
         * Get Entry.
         *
         * @return - List<Field>.
         */
        public List<Field> getEntry() {
            return this.entries;
        }

        /**
         * Set Entry.
         *
         * @param entries - List<Field>.
         */
        public void setEntry(List<Field> entries) {
            this.entries = entries;
        }

    }

    /**
     * Field.
     */
    @XmlRootElement
    public static class Field {
        private int field;

        /**
         * Constructor.
         *
         * @param field - field.
         */
        public Field(int field) {
            this.field = field;
        }

        /**
         * Constructor.
         */
        public Field() {
        }

        /**
         * Get Field.
         *
         * @return - int.
         */
        public int getField() {
            return field;
        }

        /**
         * Set Field.
         *
         * @param field - field.
         */
        public void setField(int field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "Field{" + "field=" + field + '}';
        }
    }
}
