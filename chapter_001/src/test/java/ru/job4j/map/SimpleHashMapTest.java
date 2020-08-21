package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        hm.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        hm.insert(5, 5);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = hm.iterator();
        hm.insert(3, 5);
        it.next();
    }

    @Test
    public void whenAddThenIt() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        for (int i = 0; i < 20; i++) {
            hm.insert(i, i);
        }
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = hm.iterator();
        SimpleHashMap.Node<Integer, Integer> next1 = it.next();
        assertThat(next1.getKey(), is(0));
        assertThat(next1.getValue(), is(0));
        SimpleHashMap.Node<Integer, Integer> next2 = it.next();
        assertThat(next2.getKey(), is(1));
        assertThat(next2.getValue(), is(1));
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<>();
        hm.insert(1, 1);
        Iterator<SimpleHashMap.Node<Integer, Integer>> it = hm.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }
}
