package ru.job4j.spring.di.task1;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<String> data = new ArrayList<>();

    public void add(String value) {
        data.add(value);
    }

    public List<String> getAll() {
        return data;
    }
}