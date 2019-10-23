package ru.job4j.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 08.10.2019.
 */
@XmlRootElement
public class Jobs {
    private List<Job> jobs;

    /**
     * Constructor for JAXB.
     */
    public Jobs() {
    }

    /**
     * Constructor.
     */
    public Jobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * Get list.
     *
     * @return - list of job.
     */
    @XmlElement(name = "job")
    public List<Job> getJobs() {
        return this.jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    @XmlRootElement
    @XmlType(propOrder = {"id", "device", "user", "type", "amount", "date"})
    public static class Job {
        private int id;
        private String device;
        private String user;
        private String type;
        private int amount;
        private String date;
        private final LocalDateTime local = LocalDateTime.now();
        private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");


        /**
         * Constructor for JAXB.
         */
        public Job() {
        }

        /**
         * Constructor. (Use only DB)
         *
         * @param id - id.
         * @param device - device.
         * @param user - user.
         * @param type - type.
         * @param amount - amount.
         * @param date - date.
         */
        public Job(int id, String device, String user, String type, int amount,  String date) {
            this(device, user, type, amount);
            this.id = id;
            this.date = date;
        }

        /**
         * Constructor.
         *
         * @param device - device.
         * @param user - user.
         * @param type - type.
         * @param amount - amount.
         */
        public Job(String device, String user, String type, int amount) {
            this.device = device;
            this.user = user;
            this.type = type;
            this.amount = amount;
            this.date = FORMATTER.format(this.local);
        }

        public void setId(int id) {
            this.id = id;
        }

         @XmlElement(name = "jobId")
        public int getId() {
            return id;
        }

        public String getDevice() {
            return device;
        }

        public String getUser() {
            return user;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }

        public String getDate() {
            return date;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
