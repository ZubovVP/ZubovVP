package ru.job4j.trade;

import java.util.*;

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
    private Map<Double, Application> saleSortTable = new TreeMap<>(doubleDecrease);

    /**
     * Add application in the container.
     *
     * @param application - application.
     */
    public void addApplication(Application application) {
        if (application.getAction().equals("buy")) {
            for (Map.Entry<Double, Application> entry : this.saleSortTable.entrySet()) {
                if (entry.getValue().getPrice() <= application.getPrice()) {
                    if (entry.getValue().getVolume() >= application.getVolume()) {
                        entry.getValue().setVolume(entry.getValue().getVolume() - application.getVolume());
                        application.setVolume(0);
                        break;
                    } else {
                        application.setVolume(application.getVolume() - entry.getValue().getVolume());
                        this.saleSortTable.remove(entry.getKey());
                        break;
                    }
                }
            }
                if (this.buySortTable.get(application.getPrice()) != null && application.getVolume() > 0) {
                    this.buySortTable.get(application.getPrice()).setVolume(this.buySortTable.get(application.getPrice()).getVolume() + application.getVolume());
                } else if (this.buySortTable.get(application.getPrice()) == null && application.getVolume() > 0) {
                    this.buySortTable.put(application.getPrice(), application);
                }
        } else if (application.getAction().equals("sale")) {
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
            if (this.saleSortTable.get(application.getPrice()) != null && application.getVolume() > 0) {
                this.saleSortTable.get(application.getPrice()).setVolume(this.saleSortTable.get(application.getPrice()).getVolume() + application.getVolume());
            } else  if (this.saleSortTable.get(application.getPrice()) == null && application.getVolume() > 0) {
                this.saleSortTable.put(application.getPrice(), application);
            }
        }
    }

    /**
     * Delete application from the container.
     *
     * @param application - application.
     */
    public void deleteApplication(Application application) {
        if (application.getAction().equals("buy")) {
            this.buySortTable.remove(application.getPrice());
        } else if (application.getAction().equals("sale")) {
            this.saleSortTable.remove(application.getPrice());
        } else {
            System.out.println("Please, write correct type of application");
        }
    }

    /**
     * Print all the application.
     */
    public void printApplications() {
        Iterator<Map.Entry<Double, Application>> itrBuy = this.buySortTable.entrySet().iterator();
        printTable(itrBuy);
        printTable(this.saleSortTable.entrySet().iterator());
    }

    private void printTable(Iterator<Map.Entry<Double, Application>> itr) {
        Application apl;
        StringBuilder builder = new StringBuilder();
       while (itr.hasNext()) {
           apl = itr.next().getValue();
           if (apl.getAction().equals("buy")) {
               builder.append(apl.getBook() + System.getProperty("line.separator"));
               builder.append("Buy - Price" + System.getProperty("line.separator"));
               builder.append(apl.getVolume() + " - " + apl.getPrice() + System.getProperty("line.separator"));
               builder.append("---------------------" + System.getProperty("line.separator"));
           } else {
               builder.append(apl.getBook() + System.getProperty("line.separator"));
               builder.append("Sale - Price" + System.getProperty("line.separator"));
               builder.append(apl.getVolume() + " - " + apl.getPrice() + System.getProperty("line.separator"));
               builder.append("---------------------");
           }
       }
        System.out.print(builder.toString());
    }
}