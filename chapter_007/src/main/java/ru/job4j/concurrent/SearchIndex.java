package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {

    private T[] array;
    private int firstIndex;
    private int desiredIndex;

    public SearchIndex() {

    }

    private SearchIndex(T[] array, int firstIndex, int desiredIndex) {
        this.array = array;
        this.firstIndex = firstIndex;
        this.desiredIndex = desiredIndex;
    }

    @Override
    protected Integer compute() {
        if (array.length <= 10) {
            return linearSearch();
        } else {
            return createSubtasks();
        }
    }

    private Integer createSubtasks() {
        int length = array.length;
        int mid = length / 2;

        int left = createSubtask(0, mid, firstIndex);
        int right = createSubtask(mid, length, firstIndex + mid);
        return left != -1
                ? left
                : right;
    }

    private int createSubtask(int from, int to, int firstIndex) {
        T[] leftPart1 = Arrays.copyOfRange(array, from, to);
        SearchIndex<T> leftSort = new SearchIndex<>(leftPart1, firstIndex, this.desiredIndex);
        leftSort.fork();
        return leftSort.join();
    }

    private int linearSearch() {
        for (int i = 0; i < array.length; i++) {
            int index = i + firstIndex;
            if (desiredIndex == index) {
                return index;
            }
        }
        return -1;
    }

    public static <R> int search(R[] array, int desiredIndex) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new SearchIndex<>(array, 0, desiredIndex));
    }
}
