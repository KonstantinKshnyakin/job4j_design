package ru.job4j.srp.report;

import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.Map;
import java.util.function.Predicate;

public class ReportEngine {

    private final Store store;
    private static final String NAME = "Name";
    private static final String HIRED = "Hired";
    private static final String FIRED = "Fired";
    private static final String SALARY = "Salary";
    private static final String NAME_FORMAT = "%-12s";
    private static final String DATE_FORMAT = "%-12s";
    private static final String SALARY_FORMAT = "%-7.2f";
    private static final String SALARY_STR_FORMAT = "%-7s";
    private final Map<String, String> formatMap = Map.of(
            NAME, NAME_FORMAT,
            HIRED, DATE_FORMAT,
            FIRED, DATE_FORMAT,
            SALARY, SALARY_STR_FORMAT);

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}