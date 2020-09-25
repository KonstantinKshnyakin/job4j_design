package ru.job4j.menu.outer;

import java.util.List;

public interface InputOutput extends Input, Output {

    List<Integer> ask(String question);
}
