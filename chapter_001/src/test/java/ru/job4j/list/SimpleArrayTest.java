package ru.job4j.list;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenSize() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("str1");
        array.add("str2");
        array.add("str3");
        assertThat(array.size(), is(3));
    }

    @Test
    public void whenRemove() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("str1");
        array.add("str2");
        array.add("str3");
        array.remove(1);
        assertThat(array.size(), is(2));
        assertThat(array.get(0), is(("str1")));
        assertThat(array.get(1), is(("str3")));
    }

    @Test
    public void whenSet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("str1");
        array.add("str2");
        array.add("str3");
        array.set(0, "str5");
        assertThat(array.size(), is(3));
        assertThat(array.get(0), is("str5"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}