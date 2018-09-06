package ru.job4j.wait;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests.
 * Created by ZubovVP on 03.06.2018
 * zubovvp@yadndex.ru
 */
public class ProducerConsumerTest {
    private final ProducerConsumer pc = new ProducerConsumer(5);
    private final List<Integer> counts = new ArrayList<>();


    @Before
    public void start() {
        Collections.addAll(counts, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    @Test
    public void testProducerConsumerUseTwoThreads() throws InterruptedException {
        List<Integer> results = new ArrayList<>();

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    try {
                        pc.produce(counts.get(x));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < 10; x++) {
                    try {
                        results.add((Integer) pc.consume());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        for (int y = 0; y < results.size(); y++) {
            assertThat(counts.get(y), is(results.get(y)));
        }
    }
}