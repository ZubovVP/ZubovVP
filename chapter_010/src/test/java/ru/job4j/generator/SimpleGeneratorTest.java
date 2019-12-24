package ru.job4j.generator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 22.12.2019.
 */
public class SimpleGeneratorTest {

    @Test
    public void writeCorrectLineAndCorrectMap() {
        String testLine = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        map.put("subject", "you");
        SimpleGenerator generator = new SimpleGenerator();
        String result = null;
        try {
            result = generator.checkLine(map, testLine);
        } catch (CheckExeption checkExeption) {
            checkExeption.printStackTrace();
        }
        assertThat(result, is("I am a Igor, Who are you?"));
    }

    @Test(expected = CheckExeption.class)
    public void writeLineWithNotExistMarkerShouldException() throws CheckExeption {
        String testLine = "Where is my ${object}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        map.put("subject", "you");
        SimpleGenerator generator = new SimpleGenerator();
        generator.checkLine(map, testLine);
    }

    @Test
    public void writeLineWithDoNotUseAllMarkersFromMapShouldException() {
        String testLine = "Where is my ${object}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        map.put("subject", "you");
        map.put("object", "you");
        SimpleGenerator generator = new SimpleGenerator();
        try {
            generator.checkLine(map, testLine);
            fail("Expected CheckExeption");
        } catch (CheckExeption checkExeption) {
            assertThat(checkExeption.getMessage(), is("Don't use all keys in the data."));
        }
    }
}