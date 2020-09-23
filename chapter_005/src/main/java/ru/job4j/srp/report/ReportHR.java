package ru.job4j.srp.report;

import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.srp.Constant.*;

public class ReportHR implements Report {

    private final Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String createReport(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findByFilterAndSortedByCompare(filter,
                Comparator.comparingDouble(Employee::getSalary));
        text.append(getTitle());
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(getName(employee))
                    .append(getSalary(employee));
        }
        return text.toString();
    }

    private String getTitle() {
        return String.format(NAME_FORMAT + SALARY_STR_FORMAT, NAME, SALARY);
    }

    private String getName(Employee employee) {
        return String.format(NAME_FORMAT, employee.getName());
    }

    private String getSalary(Employee employee) {
        return String.format(SALARY_FORMAT, employee.getSalary());
    }
}
