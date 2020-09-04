package ru.job4j.conrol;

import java.nio.file.Path;
import java.util.Map;
import java.util.function.Predicate;

import static ru.job4j.conrol.StrConstant.*;

public class SearchPredicate {

    private String name;
    private final Map<String, Predicate<Path>> predicateMap = Map.of(
            M, p -> p.toFile().getName().endsWith(name),
            F, p -> p.toFile().getName().equals(name),
            R, p -> p.toFile().getName().matches(name)
    );

    public SearchPredicate(String name) {
        this.name = name;
    }

    public Predicate<Path> getPredicate(String searchMethod) {
        return predicateMap.getOrDefault(searchMethod, p -> p.toFile().getName().endsWith(name));
    }
}
