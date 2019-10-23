package ru.job4j.servlets;

import org.codehaus.jackson.map.ObjectMapper;
import ru.job4j.converter.ConvertXML;
import ru.job4j.models.Jobs;
import ru.job4j.storage.Actions;
import ru.job4j.storage.Operator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.10.2019.
 */
public class ServiceServlet extends HttpServlet {
    private Actions operator = Operator.getInstance();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        List jobs = null;
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        if (name == null) {
            jobs = operator.getAllJob();
        } else if (action.equals("findByUser")) {
            jobs = operator.findByUser(name);
        } else if (action.equals("findByDevice")) {
            jobs = operator.findByDevice(name);
        } else if (action.equals("findByType")) {
            jobs = operator.findByType(name);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(jobs);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            writer.print(json);
            writer.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*"); // Cros
        String device = req.getParameter("device");
        String userName = req.getParameter("username");
        String type = req.getParameter("type");
        int amount = Integer.parseInt(req.getParameter("amount"));
        ArrayList<Jobs.Job> jobList = new ArrayList<>();
        jobList.add(new Jobs.Job(device, userName, type, amount));
        ConvertXML convertXML = new ConvertXML(new File((String.format("%s%s%s", System.getProperty("user.dir"), "\\NewJob.xml", this.counter.getAndIncrement()))));
        File xmlJob = convertXML.conver(jobList);
        this.operator.add(xmlJob);
        if (xmlJob.exists()) {
            xmlJob.delete();
        }
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        this.operator.close();
        super.destroy();
    }
}
