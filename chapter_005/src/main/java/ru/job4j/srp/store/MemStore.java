package ru.job4j.srp.store;

import ru.job4j.srp.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee em) {
        employees.add(em);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findByFilterAndSortedByCompare(Predicate<Employee> filter,
                                                         Comparator<Employee> comparator) {
        List<Employee> list = findBy(filter);
        list.sort(comparator);
        return list;
    }
}
