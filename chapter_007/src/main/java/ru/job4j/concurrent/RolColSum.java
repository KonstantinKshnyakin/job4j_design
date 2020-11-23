package ru.job4j.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int length = Math.max(matrix.length, matrix[0].length);
        Sums[] sums = new Sums[length];
        Arrays.setAll(sums, i -> new Sums());
        for (int i = 0; i < length; i++) {
            sums[i].setRowSum(rowSum(matrix, i));
            sums[i].setColSum(colSum(matrix, i));
        }
        return sums;
    }

    private static int rowSum(int[][] matrix, int rowNum) {
        int sum = 0;
        if (rowNum < matrix.length) {
            for (int i : matrix[rowNum]) {
                sum += i;
            }
        }
        return sum;
    }

    private static int colSum(int[][] matrix, int colNum) {
        int sum = 0;
        for (int[] row : matrix) {
            if (colNum < row.length) {
                sum += row[colNum];
            }
        }
        return sum;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int length = Math.max(matrix.length, matrix[0].length);
        Sums[] sums = new Sums[length];
        Arrays.setAll(sums, i -> new Sums());
        ArrayList<CompletableFuture<Sums>> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(getTask(matrix, i));
        }
        for (int i = 0; i < length; i++) {
            sums[i] = list.get(i).get();
        }
        return sums;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums sums = new Sums();
            sums.setRowSum(rowSum(matrix, i));
            sums.setColSum(colSum(matrix, i));
            return sums;
        });
    }


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
    }
}
















