package ru.job4j.srp.report;

import org.joda.time.LocalDate;
import ru.job4j.srp.Employee;
import ru.job4j.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

import static ru.job4j.srp.Constant.*;

public class ReportBookkeeping implements Report {

    private final Store store;

    public ReportBookkeeping(Store store) {
        this.store = store;
    }

    @Override
    public String createReport(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> sortedList = store.findBy(filter);
        text.append(getTitle());
        for (Employee employee : sortedList) {
            text.append(System.lineSeparator())
                    .append(getName(employee))
                    .append(getDate(employee.getHired()))
                    .append(getDate(employee.getFired()))
                    .append(getSalary(employee));
        }
        return text.toString();
    }

    private String getTitle() {
        return String.format(NAME_FORMAT + DATE_FORMAT + DATE_FORMAT + SALARY_STR_FORMAT,
                NAME, HIRED, FIRED, SALARY);
    }

    private String getName(Employee employee) {
        return String.format(NAME_FORMAT, employee.getName());
    }

    private String getSalary(Employee employee) {
        return String.format(SALARY_FORMAT, employee.getSalary() * 1000);
    }

    private String getDate(LocalDate date) {
        return String.format(DATE_FORMAT, date);
    }
}
