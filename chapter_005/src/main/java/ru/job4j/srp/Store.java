package ru.job4j.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);

    List<Employee> findByFilterAndSortedByCompare(Predicate<Employee> filter,
                                                         Comparator<Employee> comparator);
}
