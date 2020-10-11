package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.concurrent.nonblock.Base;
import ru.job4j.concurrent.nonblock.NonblockingCache;

import static org.hamcrest.Matchers.is;

public class CashTest {

    @Test
    public void whenUpdate() throws InterruptedException {
        NonblockingCache cash = new NonblockingCache();
        Base data1 = new Base("sda34rfw43");
        cash.add(data1);
        Thread first = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    cash.update(data1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    cash.update(data1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        first.start();
        second.start();
        first.join();
        second.join();
        Assert.assertThat(data1.getVersion(), is(7));
    }
}
