package ru.job4j.srp;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
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

    public String generateForProgrammers(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append("<html>").append(System.lineSeparator());
        text.append(getTitle(NAME, HIRED, FIRED, SALARY));
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(getName(employee))
                    .append(getDate(employee.getHired()))
                    .append(getDate(employee.getFired()))
                    .append(getSalary(employee));
        }
        text.append(System.lineSeparator()).append("</html>");
        return text.toString();
    }

    public String generateForBookkeeping(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append(getTitle(NAME, HIRED, FIRED, SALARY));
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(getName(employee))
                    .append(getDate(employee.getHired()))
                    .append(getDate(employee.getFired()))
                    .append(getSalaryForBookkeeping(employee));
        }
        return text.toString();
    }

    public String generateForHR(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findByFilterAndSortedByCompare(filter,
                Comparator.comparingDouble(Employee::getSalary));
        text.append(getTitle(NAME, SALARY));
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(getName(employee))
                    .append(getSalary(employee));
        }
        return text.toString();
    }

    private String getTitle(String... fields) {
        StringBuilder result = new StringBuilder();
        for (String field : fields) {
            result.append(String.format(formatMap.get(field), field));
        }
        return result.toString();
    }

    private String getSalaryForBookkeeping(Employee emp) {
        return String.format(SALARY_FORMAT, emp.getSalary() * 1000);
    }

    private String getSalary(Employee emp) {
        return String.format(SALARY_FORMAT, emp.getSalary());
    }

    private String getName(Employee emp) {
        return String.format(NAME_FORMAT, emp.getName());
    }

    private String getDate(LocalDate date) {
        return String.format(DATE_FORMAT, date);
    }
}