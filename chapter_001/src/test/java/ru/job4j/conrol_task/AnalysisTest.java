package ru.job4j.conrol_task;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;

public class AnalysisTest {

    @Test
    public void whenAddThen2() {
        ArrayList<User> prevList = new ArrayList<>();
        prevList.add(new User(1, "1"));
        prevList.add(new User(2, "2"));
        ArrayList<User> currList = new ArrayList<>();
        currList.add(new User(1, "1"));
        currList.add(new User(2, "2"));
        currList.add(new User(3, "3"));
        currList.add(new User(4, "4"));
        Info diff = Analysis.diff(prevList, currList);
        Assert.assertThat(diff.getAdded(), is(2));
    }

    @Test
    public void whenDeletedThen3() {
        ArrayList<User> prevList = new ArrayList<>();
        prevList.add(new User(5, "5"));
        prevList.add(new User(4, "4"));
        prevList.add(new User(2, "2"));
        prevList.add(new User(3, "3"));
        prevList.add(new User(1, "1"));
        ArrayList<User> currList = new ArrayList<>();
        currList.add(new User(1, "1"));
        currList.add(new User(2, "2"));
        Info diff = Analysis.diff(prevList, currList);
        Assert.assertThat(diff.getDeleted(), is(3));
    }

    @Test
    public void whenChangedThen2() {
        ArrayList<User> prevList = new ArrayList<>();
        prevList.add(new User(5, "5"));
        prevList.add(new User(4, "4"));
        prevList.add(new User(2, "2"));
        prevList.add(new User(3, "3"));
        prevList.add(new User(1, "1"));
        ArrayList<User> currList = new ArrayList<>();
        currList.add(new User(1, "1"));
        currList.add(new User(2, "2"));
        currList.add(new User(5, "7"));
        currList.add(new User(4, "6"));
        Info diff = Analysis.diff(prevList, currList);
        Assert.assertThat(diff.getChanged(), is(2));
    }


    @Test
    public void whenChangedThen222() {
        ArrayList<User> prevList = new ArrayList<>();
        prevList.add(new User(1, "1"));
        prevList.add(new User(2, "2"));
        prevList.add(new User(3, "3"));
        prevList.add(new User(4, "4"));
        prevList.add(new User(5, "5"));
        ArrayList<User> currList = new ArrayList<>();
        currList.add(new User(3, "9"));
        currList.add(new User(4, "10"));
        currList.add(new User(5, "5"));
        currList.add(new User(6, "6"));
        currList.add(new User(7, "7"));
        Info diff = Analysis.diff(prevList, currList);
        Assert.assertThat(diff.getAdded(), is(2));
        Assert.assertThat(diff.getChanged(), is(2));
        Assert.assertThat(diff.getDeleted(), is(2));
    }
}
