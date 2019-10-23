package ru.job4j.converter;

import ru.job4j.models.Jobs;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 16.10.2019.
 */
public class ConvertXML {
    private final File target;

    /**
     * Constructor.
     *
     * @param target - way.
     */
    public ConvertXML(File target) {
        this.target = target;
    }

    /**
     * Convert jobs(job) to XML.
     *
     * @param list - list of Job.
     */
    public File conver(List<Jobs.Job> list) {
        try {
            JAXBContext context = JAXBContext.newInstance(Jobs.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Jobs(list), this.target);
        } catch (JAXBException e1) {
            e1.printStackTrace();
        }
        return this.target;
    }

    /**
     * Convert XML to jobs.
     *
     * @return - Jobs
     */
    public Jobs unpack() {
        Jobs result = null;
        if (this.target.exists()) {
            try {
                JAXBContext context = JAXBContext.newInstance(Jobs.class);
                Unmarshaller unMarshaller = context.createUnmarshaller();
                result = (Jobs) unMarshaller.unmarshal(this.target);
            } catch (JAXBException e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }
}
