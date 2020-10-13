package ru.job4j.concurrent;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class SearchIndexTest {

    @Test
    public void whenDesiredIndexIsPresent() {
        String[] arr = RandomStringUtils.randomAlphabetic(100).split("");
        for (int i = 0; i < 100; i++) {
            int searchIndex = SearchIndex.search(arr, i);
            Assert.assertThat(searchIndex, is(i));
        }
    }

    @Test
    public void whenDesiredIndexNotPresent() {
        String[] arr = RandomStringUtils.randomAlphabetic(200).split("");
        int desiredIndex = 1999;
        int searchIndex = SearchIndex.search(arr, desiredIndex);
        Assert.assertThat(searchIndex, is(-1));
    }
}
