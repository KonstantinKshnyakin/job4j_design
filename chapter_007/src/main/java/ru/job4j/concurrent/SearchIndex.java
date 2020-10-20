package ru.job4j.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchIndex<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int firstIndex;
    private final int desiredIndex;
    private final int size;

    private SearchIndex(T[] array, int firstIndex, int desiredIndex, int size) {
        this.array = array;
        this.firstIndex = firstIndex;
        this.desiredIndex = desiredIndex;
        this.size = size;
    }

    @Override
    protected Integer compute() {
        if (size <= 10) {
            return linearSearch();
        } else {
            return createSubtasks();
        }
    }

    private Integer createSubtasks() {
        int mid = size / 2;

        int left = createSubtask(mid, firstIndex);
        int right = createSubtask(size - mid, firstIndex + mid);
        return left != -1
                ? left
                : right;
    }

    private int createSubtask(int size, int firstIndex) {
        SearchIndex<T> newTask = new SearchIndex<>(array, firstIndex, desiredIndex, size);
        newTask.fork();
        return newTask.join();
    }

    private int linearSearch() {
        for (int i = firstIndex; i < firstIndex + size; i++) {
            if (desiredIndex == i) {
                return i;
            }
        }
        return -1;
    }

    public static <R> int search(R[] array, int desiredIndex) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new SearchIndex<>(array, 0, desiredIndex, array.length));
    }
}
