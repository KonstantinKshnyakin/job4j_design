package ru.job4j.spring.di.task4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.spring.di.task4");
        context.refresh();

        StartUI ui = context.getBean(StartUI.class);
        ui.add("Petr Arsentev");
        ui.add("Ivan ivanov");
        ui.readFromConsole();
        ui.print();
    }
}