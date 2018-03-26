package ru.job4j.sort;

import java.util.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    private List<String> directory = new ArrayList<>();
    private StringBuilder builder = new StringBuilder();


    /**
     * Constructor.
     * @param directory - directory.
     */
    public Department(List<String> directory) {
        this.directory = directory;
    }

    /**
     * Get directory.
     * @return List<String> - directory.
     */
    public List<String> getDirectory() {
        return this.directory;
    }

    /**
     * Add subdivision in directory.
     * @param subdivision - subdivision.
     */
    public void addSubdivision(String subdivision) {
        String[] variants = delimetr(subdivision);
        for (String ty : variants) {
            builder.append(ty);
            if (!directory.contains(ty)) {
                directory.add(builder.toString());
            }
            builder.append("\\");
        }
    }

    /**
     * Remove subdivision from directory.
     * @param subdivision - subdivision.
     */
    public void deleteSubdivision(String subdivision) {
        List<String> deleteList = new ArrayList<>();
        for (String result : directory) {
            String[] variants = delimetr(result);
            for (String srt : variants) {
                builder.append(srt);
                if (builder.toString().equals(subdivision)) {
                    deleteList.add(result);
                    break;
                }
                builder.append("\\");

            }
            builder.setLength(0);
        }
            directory.removeAll(deleteList);
    }


    /**
     * Separation subdivision.
     * @param subdivision - subdivision.
     * @return String[] - subdivision.
     */
     private String[] delimetr(String subdivision) {
         String delimentr = "\\\\";
         return subdivision.split(delimentr);
     }

    /**
     *  Sort ascendeng.
     */
     public void sortAscendeng() {
         Collections.sort(directory);
     }

    /**
     * Sort decreasing.
     */
     public void sortDecreasing() {
           Comparator<String> nameComparator = new Comparator<String>() {

               @Override
               public int compare(String o1, String o2) {
                   String[] o1t = delimetr(o1);
                   String[] o2t = delimetr(o2);
                   int result = 0;
                 for (int x = 0; x < Math.min(o1t.length, o2t.length); x++) {
                     if (o1t[x].equals(o2t[x])) {
                         continue;
                     } else if (o1t[x].endsWith(o2t[x])) {
                         result = 1;
                         break;
                     } else {
                         result = -1;
                         break;
                     }
                 }
                 return result;
               }
           };
         Collections.sort(directory, nameComparator);
     }
}
