package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.01.2021.
 */
@XmlRootElement(name = "company")
@XmlAccessorType(XmlAccessType.FIELD)
public class Company {
    private String name;
    @XmlAttribute
    private int foundationYear;
    @XmlAttribute
    private boolean work;
    private Owner owner;
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Company(String name, int foundationYear, boolean work, Owner owner, List<Employee> employee) {
        this.name = name;
        this.foundationYear = foundationYear;
        this.work = work;
        this.owner = owner;
        this.employees = employee;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public boolean isWork() {
        return work;
    }

    public Owner getOwner() {
        return owner;
    }

    public List<Employee> getEmployee() {
        return employees;
    }

    @Override
    public String toString() {
        return "Company{" + "name='" + name + '\'' + ", foundationYear=" + foundationYear + ", work=" + work + ", owner=" + owner + ", employee=" + employees + '}';
    }

    static class Owner {
        @XmlAttribute
        private String name;

        public Owner(String name) {
            this.name = name;
        }

        public Owner() {
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Owner{" + "name='" + name + '\'' + '}';
        }
    }

    public static void main(String[] args) throws JAXBException {
        Company company = new Company("Tech", 1999, Boolean.TRUE, new Owner("Rick"), (Arrays.asList(new Employee("Mike"), new Employee("Sam"), new Employee("Ted"), new Employee("Ann"))));
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(Company.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(company, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Для сериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            // десериализуем
            Company result = (Company) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

        //marshaller.marshal(book, new File("./book.xml"));
    }
}
