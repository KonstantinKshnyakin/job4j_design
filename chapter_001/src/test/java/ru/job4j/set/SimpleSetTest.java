package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void addEqualsElement() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("str1");
        simpleSet.add("str2");
        simpleSet.add("str3");
        assertThat(simpleSet.size(), is(3));
        simpleSet.add("str3");
        assertThat(simpleSet.size(), is(3));
    }

    @Test
    public void whenMultiCallhasNextThenTrue() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("str1");
        Iterator<String> iterator = simpleSet.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        simpleSet.add("str1");
        simpleSet.add("str2");
        simpleSet.add("str3");
        Iterator<String> iterator = simpleSet.iterator();
        assertThat(iterator.next(), is("str1"));
        assertThat(iterator.next(), is("str2"));
        assertThat(iterator.next(), is("str3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        SimpleSet<String> simpleSet = new SimpleSet<>();
        Iterator<String> iterator = simpleSet.iterator();
        iterator.next();
    }
}
