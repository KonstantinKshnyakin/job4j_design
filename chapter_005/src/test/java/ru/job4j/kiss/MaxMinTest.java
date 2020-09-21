package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MaxMinTest {

    private ArrayList<Integer> arr;
    private MaxMin maxMin;

    @Before
    public void setup() {
        maxMin = new MaxMin();
        arr = new ArrayList<>();
        arr.add(13);
        arr.add(1);
        arr.add(27);
        arr.add(55);
        arr.add(9);
    }

    @Test
    public void whenMinThen1() {
        Optional<Integer> min = maxMin.min(arr, Integer::compareTo);
        assertThat(min.isPresent(), is(true));
        assertThat(min.get(), is(1));
    }

    @Test
    public void whenMaxThen55() {
        Optional<Integer> max = maxMin.max(arr, Integer::compareTo);
        assertThat(max.isPresent(), is(true));
        assertThat(max.get(), is(55));
    }

    @Test
    public void whenSizeZeroThenEmpty() {
        ArrayList<Integer> arrEmpty = new ArrayList<>();
        Optional<Integer> max = maxMin.max(arrEmpty, Integer::compareTo);
        assertThat(max.isPresent(), is(false));
    }

    @Test
    public void whenArrNullThenEmpty() {
        Optional<Integer> max = maxMin.max(null, Integer::compareTo);
        assertThat(max.isPresent(), is(false));
    }
}
