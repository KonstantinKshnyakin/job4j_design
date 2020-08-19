package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    @Test
    public void whenAddThenIncreaseSize() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        hm.insert(1, 1);
        hm.insert(2, 2);
        assertThat(hm.size(), is(2));
    }

    @Test
    public void whenAddTwoEqualsElementThenAddedOne() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        hm.insert(1, 1);
        hm.insert(1, 1);
        assertThat(hm.size(), is(1));
    }

    @Test
    public void whenAddMore16ElementThenAddedAll() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.insert(i, i);
        }
        assertThat(hm.size(), is(20));
    }

    @Test
    public void whenGetThenReturn() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.insert(i, i);
        }
        assertThat(hm.get(9), is(9));
    }

    @Test
    public void whenDeleteThenAddedAll() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.insert(i, i);
        }
        assertThat(hm.delete(8), is(true));
        assertThat(hm.size(), is(19));
        assertThat(hm.get(8), is(nullValue()));
    }
}
