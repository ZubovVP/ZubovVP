package ru.job4j.generator;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.12.2019.
 */
public class SimpleGenerator implements Checkable {
    private static final Pattern PATTERN = Pattern.compile("\\$\\{\\w*}");
    private Validator validator;

    /**
     * Constructor.
     */
    public SimpleGenerator() {
        this.validator = new Validator();
    }

    /**
     * Replace markers on the correct words.
     *
     * @param data - collection with words.
     * @param line - line with markers.
     * @return - correct line.
     * @throws CheckExeption - if markers in the collection don't use or key of marker don't exist on the collection.
     */
    @Override
    public String checkLine(Map<String, String> data, String line) throws CheckExeption {
        Set<String> keys = new LinkedHashSet<>();
        StringBuilder sb = new StringBuilder();
        Matcher matcher = PATTERN.matcher(line);
        String value;
        String result;
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            keys.add(key);
            if (!this.validator.checkKey(data, key)) {
                throw new CheckExeption(String.format("This key  '%s' ,doesn't exist in the data.", key));
            }
        }
        if (this.validator.containsKeys(data, keys)) {
            matcher = PATTERN.matcher(line);
            Iterator<String> itr = keys.iterator();
            while (matcher.find()) {
                value = data.get(itr.next());
                matcher.appendReplacement(sb, value);
            }
            matcher.appendTail(sb);
            result = sb.toString();
        } else {
            throw new CheckExeption("Don't use all keys in the data.");
        }
        return result;
    }
}
