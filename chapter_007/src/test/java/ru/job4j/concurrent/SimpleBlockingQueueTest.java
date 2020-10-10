package ru.job4j.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whenElementsMoreThanQueueSize() throws InterruptedException {
        List<Integer> inList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        List<Integer> outList = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            try {
                for (Integer integer : inList) {
                    queue.offer(integer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                while (outList.size() != inList.size()) {
                    outList.add(queue.poll());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(outList, is(inList));
    }

    @Test
    public void whenInputElementsZero() throws InterruptedException {
        List<Integer> inList = new ArrayList<>();
        List<Integer> outList = new ArrayList<>();
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            try {
                for (Integer integer : inList) {
                    queue.offer(integer);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                while (outList.size() != inList.size()) {
                    outList.add(queue.poll());

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(inList, is(outList));
    }
} 