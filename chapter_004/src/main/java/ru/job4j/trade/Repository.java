package ru.job4j.trade;

import java.util.*;

import static ru.job4j.trade.Action.*;
import static ru.job4j.trade.Application.*;

/**
 * Repository.
 *
 * @author Vitaly Zubov (zubovvp@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Repository {
    private Map<Double, Application> buySortTable = new TreeMap<>(doubleDecrease);
    private Map<Double, Application> sellSortTable = new TreeMap<>(doubleDecrease);

    /**
     * Add application in the container.
     *
     * @param application - application.
     */
    public void addApplication(Application application) {
        if (application.getAction().equals(BUY.getName())) {
            for (Map.Entry<Double, Application> entry : this.sellSortTable.entrySet()) {
                if (entry.getValue().getPrice() <= application.getPrice()) {
                    if (entry.getValue().getVolume() >= application.getVolume()) {
                        entry.getValue().setVolume(entry.getValue().getVolume() - application.getVolume());
                        application.setVolume(0);
                        break;
                    } else {
                        application.setVolume(application.getVolume() - entry.getValue().getVolume());
                        this.sellSortTable.remove(entry.getKey());
                        break;
                    }
                }
            }
            if (this.buySortTable.get(application.getPrice()) != null && application.getVolume() > 0) {
                this.buySortTable.get(application.getPrice()).setVolume(this.buySortTable.get(application.getPrice()).getVolume() + application.getVolume());
            } else if (this.buySortTable.get(application.getPrice()) == null && application.getVolume() > 0) {
                this.buySortTable.put(application.getPrice(), application);
            }
        } else if (application.getAction().equals(SELL.getName())) {
            for (Map.Entry<Double, Application> entry : this.buySortTable.entrySet()) {
                if (entry.getValue().getPrice() >= application.getPrice()) {
                    if (entry.getValue().getVolume() >= application.getVolume()) {
                        entry.getValue().setVolume(entry.getValue().getVolume() - application.getVolume());
                        application.setVolume(0);
                        break;
                    } else {
                        application.setVolume(application.getVolume() - entry.getValue().getVolume());
                        this.buySortTable.remove(entry.getKey());
                    }
                }
            }
            if (this.sellSortTable.get(application.getPrice()) != null && application.getVolume() > 0) {
                this.sellSortTable.get(application.getPrice()).setVolume(this.sellSortTable.get(application.getPrice()).getVolume() + application.getVolume());
            } else if (this.sellSortTable.get(application.getPrice()) == null && application.getVolume() > 0) {
                this.sellSortTable.put(application.getPrice(), application);
            }
        }
    }

    /**
     * Delete application from the container.
     *
     * @param application - application.
     */
    public void deleteApplication(Application application) {
        if (application.getAction().equals(BUY.getName())) {
            this.buySortTable.remove(application.getPrice());
        } else if (application.getAction().equals(SELL.getName())) {
            this.sellSortTable.remove(application.getPrice());
        } else {
            System.out.println("Please, write correct type of application");
        }
    }

    /**
     * Print all the application.
     */
    public void printApplications() {
        printTable(this.buySortTable.entrySet().iterator());
        printTable(this.sellSortTable.entrySet().iterator());
    }

    private void printTable(Iterator<Map.Entry<Double, Application>> itr) {
        Application apl;
        StringJoiner sj = new StringJoiner(System.getProperty("line.separator"),  "", "\n");
        while (itr.hasNext()) {
            apl = itr.next().getValue();
            if (apl.getAction().equals(BUY.getName())) {
                sj.add(apl.getBook());
                sj.add("Buy - Price");
                sj.add(apl.getVolume() + " - " + apl.getPrice());
                sj.add("---------------------");
            } else {
                sj.add(apl.getBook());
                sj.add("Sell - Price");
                sj.add(apl.getVolume() + " - " + apl.getPrice());
                sj.add("---------------------");
            }
        }
        if (!sj.toString().equals("\n")) {
            System.out.print(sj.toString());
        }
    }
}