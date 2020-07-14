package ru.job4j.connections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 14.07.2020.
 */
public class NetRelations {

    public Map<Integer, Set<Element>> read(String source) {
        Map<String, Integer> numbers = new HashMap<>();
        Map<Integer, Set<Element>> result = new HashMap<>();
        int count = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String line = reader.readLine();
            Scanner scanner;
            int index = 1;
            String first = null;
            String second = null;
            String third = null;
            while (line != null) {
                scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext() && index < 4) {
                    String a = scanner.next().replace("\"", "");
                    if (index == 1) {
                        first = a;
                    } else if (index == 2) {
                        second = a;
                    } else if (index == 3) {
                        third = a;
                    }
                    index++;
                }
                if (first == null || second == null || third == null || first.equals("") && second.equals("") && third.equals("")) {
                    line = reader.readLine();
                    index = 1;
                    continue;
                }
                Element element = new Element(first, second, third);
                if (!first.equals("") && numbers.containsKey(first)) {
                    result.get(numbers.get(first)).add(element);
                } else if (!second.equals("") && numbers.containsKey(second)) {
                    result.get(numbers.get(second)).add(element);
                } else if (!third.equals("") && numbers.containsKey(third)) {
                    result.get(numbers.get(third)).add(element);
                } else {
                    Set<Element> newSet = new HashSet<>();
                    newSet.add(element);
                    result.put(count, newSet);
                    numbers.put(first, count);
                    numbers.put(second, count);
                    numbers.put(third, count);
                }
                count++;
                line = reader.readLine();
                index = 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    class Element {
        private final String first;
        private final String second;
        private final String third;

        public Element(String first, String second, String third) {
            this.first = first;
            this.second = second;
            this.third = third;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element that = (Element) o;
            return (Objects.equals(first, that.first) && !first.equals("")) ||
                    (Objects.equals(second, that.second) && !second.equals("")) ||
                    (Objects.equals(third, that.third) && !third.equals(""));
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second, third);
        }
    }

    public static void main(String[] args) {
        NetRelations test = new NetRelations();
        String source = String.format("%s%s", System.getProperty("user.dir"), "\\chapter_011\\lng.csv");
        test.read(source);
        long start = System.currentTimeMillis();
        Map<Integer, Set<Element>> result = test.read(source);
        long time = (System.currentTimeMillis() - start) / 1000;
        System.out.println(String.format("%s - %d\nUsed time - %d sec", "Created group - ", result.size(), time));
    }
}
