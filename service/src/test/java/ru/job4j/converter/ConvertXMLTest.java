package ru.job4j.converter;

import org.junit.After;
import org.junit.Test;
import ru.job4j.models.Jobs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 16.10.2019.
 */
public class ConvertXMLTest {
    private File target = new File((String.format("%s%s", System.getProperty("user.dir"), "\\Test.xml")));
    private ConvertXML converter = new ConvertXML(this.target);
    private Jobs.Job job1 = new Jobs.Job(1, "device1", "user1", "fax", 1, "1.01.2001");
    private Jobs.Job job2 = new Jobs.Job(2, "device2", "user2", "print", 2, "1.01.2001");

    @After
    public void finish() {
        this.target.delete();
    }

    @Test
    public void testConvertWithOneElement() {
        StringBuilder sb = new StringBuilder();
        List<Jobs.Job> listJobs = new ArrayList<>();
        listJobs.add(this.job1);
        this.converter.conver(listJobs);
        String expectation1 = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<jobs>", "    <job>", "        <jobId>1</jobId>", "        <device>device1</device>", "        <user>user1</user>", "        <type>fax</type>", "        <amount>1</amount>", "        <date>1.01.2001</date>", "    </job>", "</jobs>");
        try (BufferedReader reader = new BufferedReader(new FileReader(this.target))) {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(sb.toString(), is(expectation1));
    }

    @Test
    public void testConvertWithTwoElement() {
        StringBuilder sb = new StringBuilder();
        List<Jobs.Job> listJobs = new ArrayList<>();
        listJobs.add(this.job1);
        listJobs.add(this.job2);
        this.converter.conver(listJobs);
        String expectation1 = String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n", "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<jobs>", "    <job>", "        <jobId>1</jobId>", "        <device>device1</device>", "        <user>user1</user>", "        <type>fax</type>", "        <amount>1</amount>", "        <date>1.01.2001</date>", "    </job>", "    <job>", "        <jobId>2</jobId>", "        <device>device2</device>", "        <user>user2</user>", "        <type>print</type>", "        <amount>2</amount>", "        <date>1.01.2001</date>", "    </job>", "</jobs>");
        try (BufferedReader reader = new BufferedReader(new FileReader(this.target))) {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(sb.toString(), is(expectation1));
    }

    @Test
    public void testUnpackOneElement() {
        ArrayList<Jobs.Job> jobs = new ArrayList<>();
        jobs.add(this.job1);
        this.converter.conver(jobs);
        Jobs jobsList = this.converter.unpack();
        assertThat(jobsList.getJobs().size(), is(1));
        assertThat(jobsList.getJobs().get(0).getId(), is(this.job1.getId()));
        assertThat(jobsList.getJobs().get(0).getDevice(), is(this.job1.getDevice()));
        assertThat(jobsList.getJobs().get(0).getUser(), is(this.job1.getUser()));
        assertThat(jobsList.getJobs().get(0).getAmount(), is(this.job1.getAmount()));
    }

    @Test
    public void testUnpackTwoElements() {
        ArrayList<Jobs.Job> jobs = new ArrayList<>();
        jobs.add(this.job1);
        jobs.add(this.job2);
        this.converter.conver(jobs);
        Jobs jobsList = this.converter.unpack();
        assertThat(jobsList.getJobs().size(), is(2));
        assertThat(jobsList.getJobs().get(0).getId(), is(this.job1.getId()));
        assertThat(jobsList.getJobs().get(0).getDevice(), is(this.job1.getDevice()));
        assertThat(jobsList.getJobs().get(0).getUser(), is(this.job1.getUser()));
        assertThat(jobsList.getJobs().get(0).getAmount(), is(this.job1.getAmount()));
        assertThat(jobsList.getJobs().get(1).getId(), is(this.job2.getId()));
        assertThat(jobsList.getJobs().get(1).getDevice(), is(this.job2.getDevice()));
        assertThat(jobsList.getJobs().get(1).getUser(), is(this.job2.getUser()));
        assertThat(jobsList.getJobs().get(1).getAmount(), is(this.job2.getAmount()));
    }
}


