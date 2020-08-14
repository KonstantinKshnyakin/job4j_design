package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    private int[] testArray = {1, 5, 5, 6, 8};

    @Test
    public void whenAddTheOk() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(5);
        testArray.add(5);
        testArray.add(5);
        assertThat(testArray.size(), is(3));
    }

    @Test
    public void whenSetTheOk() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(5);
        testArray.add(5);
        testArray.add(5);
        testArray.set(1, 8);
        assertThat(testArray.size(), is(3));
        assertThat(testArray.get(1), is(8));
    }

    @Test
    public void whenRemoveTheOk() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(5);
        testArray.add(5);
        testArray.add(5);
        testArray.remove(1);
        assertThat(testArray.size(), is(2));
    }

    @Test
    public void whenGetTheOk() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(1);
        testArray.add(5);
        testArray.add(8);
        assertThat(testArray.get(0), is(1));
        assertThat(testArray.get(1), is(5));
        assertThat(testArray.get(2), is(8));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBoundsTheOk() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(1);
        testArray.add(5);
        testArray.add(8);
        assertThat(testArray.get(0), is(1));
        assertThat(testArray.get(1), is(5));
        assertThat(testArray.get(4), is(8));
    }

    @Test
    public void whenMultiCallHasNextThenTrue() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(1);
        Iterator<Integer> iterator = testArray.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.add(1);
        testArray.add(2);
        testArray.add(3);
        Iterator<Integer> iterator = testArray.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleArray<Integer> testArray = new SimpleArray<>(10);
        testArray.iterator().next();
    }
}
