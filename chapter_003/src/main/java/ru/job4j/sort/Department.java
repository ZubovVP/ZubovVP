package ru.job4j.sort;

import java.util.*;

/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 0.1
 */
public class Department {
    private List<String> directory = new ArrayList<>();

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
        char[] variants = subdivision.toCharArray();
        String result = String.valueOf(variants[0]);
        for (int x = 1; x < variants.length; x++) {
            if (variants[x] == '\\' && !directory.contains(result)) {
                this.directory.add(result);
            }
            result = result + String.valueOf(variants[x]);
            if (variants.length == x + 1 && !directory.contains(result)) {
                this.directory.add(result);
            }
        }
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
                   char[] o1t = o1.toCharArray();
                   char[] o2t = o2.toCharArray();
                   int result = 0;
                 for (int x = 0; x < Math.min(o1t.length, o2t.length); x++) {
                     if (o1t[x] == (o2t[x])) {
                         continue;
                     } else if (o1t[x] > (o2t[x])) {
                         result = -1;
                         break;
                     } else {
                         result = 1;
                         break;
                     }
                 }
                 return result;
               }
           };
         Collections.sort(directory, nameComparator);
     }
}
