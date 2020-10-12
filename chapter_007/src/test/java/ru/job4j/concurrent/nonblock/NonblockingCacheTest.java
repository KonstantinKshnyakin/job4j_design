package ru.job4j.concurrent.nonblock;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class NonblockingCacheTest {

    @Test
    public void whenUpdate() throws InterruptedException {
        NonblockingCache cash = new NonblockingCache();
        Base data1 = new Base("sda34rfw43");
        cash.add(data1);
        Runnable run = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    cash.update(data1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread first = new Thread(run);
        Thread second = new Thread(run);
        Thread third = new Thread(run);
        Thread fourth = new Thread(run);
        first.start();
        second.start();
        third.start();
        fourth.start();
        first.join();
        second.join();
        third.join();
        fourth.join();
        Assert.assertThat(data1.getVersion(), is(200));
    }
}
