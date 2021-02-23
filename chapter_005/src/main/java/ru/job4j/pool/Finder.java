package ru.job4j.pool;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 23.02.2021.
 */
public class Finder {
    private int[] array;
    private ForkJoinPool pool;

    public Finder(int[] array) {
        this.array = array;
        pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    public int findIndex(int count) {
        return this.pool.invoke(new MyForkJoinPool(0, array.length - 1, count));
    }

    private class MyForkJoinPool extends RecursiveTask<Integer> {
        int from, to, count;

        public MyForkJoinPool(int from, int to, int count) {
            this.from = from;
            this.to = to;
            this.count = count;
        }

        @Override
        protected Integer compute() {
            int result = 0;
            if (to - from <= 10) {
                for (int x = from; x < to; x++) {
                    if (array[x] == count) {
                        result = x;
                    }
                }
            } else {
                int mid = (from + to) / 2;
                MyForkJoinPool leftArray = new MyForkJoinPool(from, mid, count);
                MyForkJoinPool rightArray = new MyForkJoinPool(mid + 1, to, count);
                leftArray.fork();
                rightArray.fork();
                Integer left = leftArray.join();
                Integer right = rightArray.join();
                result = left == 0 ? right : left;
            }
            return result;
        }
    }
}
