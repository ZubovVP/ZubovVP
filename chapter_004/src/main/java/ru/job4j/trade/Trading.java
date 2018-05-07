package ru.job4j.trade;


import java.util.*;

/**
 * Trading.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Trading {
    private Map<String, Repository> table = new HashMap<>();

    /**
     * Send Application in the repository.
     *
     * @param application - application.
     */
    public void takeApplication(Application application) {
        if (this.table.get(application.getBook()) == null) {
            this.table.put(application.getBook(), new Repository());
        }
        if (application.getType().equals("add")) {
            this.table.get(application.getBook()).addApplication(application);
        } else if (application.getType().equals("delete")) {
            this.table.get(application.getBook()).deleteApplication(application);
        } else {
            System.out.println("Please, write correct type application.");
        }
    }

    /**
     * Print all the applications.
     */
    public void getTable() {
        for (Map.Entry<String, Repository> step : this.table.entrySet()) {
            step.getValue().printApplications();
        }
    }
}