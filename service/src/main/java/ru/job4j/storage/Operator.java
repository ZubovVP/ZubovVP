package ru.job4j.storage;


import ru.job4j.converter.ConvertXML;
import ru.job4j.models.Jobs;

import java.io.File;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.10.2019.
 */
public class Operator implements Actions<Jobs.Job> {
    private static final Operator INSTANCE = new Operator();
    private static DBService db = DBService.getInstance();

    public static Actions getInstance() {
        return INSTANCE;
    }

    /**
     * Constructor.
     */
    private Operator() {
    }

    @Override
    public void add(File element) {
        ConvertXML unpack = new ConvertXML(element);
        Jobs jobs = unpack.unpack();
        for (Jobs.Job job : jobs.getJobs()) {
            db.add(job);
        }
    }

    @Override
    public boolean clearAll() {
        return db.clearAll();
    }

    @Override
    public List<Jobs.Job> findByUser(String user) {
        List<Jobs.Job> result;
        if (user != null) {
            result = db.findByUser(user);
        } else {
            throw new NullPointerException("Check name of user");
        }
        return result;
    }

    @Override
    public List<Jobs.Job> findByDevice(String device) {
        List<Jobs.Job> result;
        if (device != null) {
            result = db.findByDevice(device);
        } else {
            throw new NullPointerException("Check device");
        }
        return result;
    }

    @Override
    public List<Jobs.Job> findByType(String type) {
        List<Jobs.Job> result;
        if (type != null) {
            result = db.findByType(type);
        } else {
            throw new NullPointerException("Check type");
        }
        return result;
    }

    @Override
    public List<Jobs.Job> getAllJob() {
        return db.getAllJob();
    }

    @Override
    public void close() {
        db.close();
    }
}
