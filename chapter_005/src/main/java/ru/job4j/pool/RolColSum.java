package ru.job4j.pool;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 26.02.2021.
 */

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public static int colSum(int[] matrix) {
            int result = 0;
            for (int matrix1 : matrix) {
                result += matrix1;

            }
            return result;
        }

        public static int rowSum(int[] matrix) {
            int result = 0;
            for (int matrix1 : matrix) {
                result += matrix1;

            }
            return result;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int x = 0; x < matrix.length; x++) {
            int index = x;
            int sumCol = Sums.colSum(matrix[x]);
            int sumRow = Sums.rowSum(Arrays.stream(Arrays.stream(matrix).map(arr -> arr[index]).toArray(size -> new Integer[size])).mapToInt(Integer::intValue).toArray());
            sums[x] = new Sums(sumCol, sumRow);
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) {
        List<CompletableFuture<Sums>> elements = new ArrayList<>();
        for (int x = 0; x < matrix.length; x++) {
            elements.add(work(x, matrix));
        }
        return elements.stream().map(e -> {
            try {
                return e.get();
            } catch (InterruptedException | ExecutionException e1) {
                e1.printStackTrace();
            }
            return null;
        }).toArray(Sums[]::new);
    }

    private static CompletableFuture<Sums> work(int step, int[][] matrix) {
        return CompletableFuture.supplyAsync(() -> {
            int sumCol = Sums.colSum(matrix[step]);
            int sumRow = Sums.rowSum(Arrays.stream(Arrays.stream(matrix).map(arr -> arr[step]).toArray(size -> new Integer[size])).mapToInt(Integer::intValue).toArray());
            return new Sums(sumCol, sumRow);
        });
    }
}
