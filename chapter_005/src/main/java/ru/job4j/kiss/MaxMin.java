package ru.job4j.kiss;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MaxMin {

    public <T> Optional<T> max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = p -> p < 0;
        return search(value, comparator, predicate);
    }

    public <T> Optional<T> min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = p -> p > 0;
        return search(value, comparator, predicate);
    }

    private <T> Optional<T> search(List<T> values, Comparator<T> comparator, Predicate<Integer> predicate) {
        T value = null;
        if (values != null && values.size() > 0) {
            value = values.get(0);
            for (T t : values) {
                if (predicate.test(comparator.compare(value, t))) {
                    value = t;
                }
            }
        }
        return value != null ? Optional.of(value) : Optional.empty();
    }
}