package ru.job4j.concurrent.nonblock;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.concurrent.pool.ThreadPool;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.hamcrest.Matchers.is;

public class ThreadPoolTest {

    @Test
    public void whenStartThreadPool() {
        ThreadPool threadPool = new ThreadPool(50);
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        Runnable runnable = () -> {
            for (int i = 0; i < 50; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 50; i++) {
            threadPool.work(runnable);
        }
        threadPool.start();
        threadPool.shutdown();
        Assert.assertThat(list.size(), is(2500));
    }
}
