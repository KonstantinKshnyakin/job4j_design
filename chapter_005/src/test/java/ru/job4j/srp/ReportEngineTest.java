package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.time.LocalDate;

public class ReportEngineTest {

    public static final String NAME = "Name";
    public static final String HIRED = "Hired";
    public static final String FIRED = "Fired";
    public static final String SALARY = "Salary";
    public static final String NAME_FORMAT = "%-12s";
    public static final String DATE_FORMAT = "%-12s";
    public static final String SALARY_FORMAT = "%-7.2f";
    public static final String SALARY_STR_FORMAT = "%-7s";

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        String expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator()).toString();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenGeneratedForHR() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        String expect = new StringBuilder()
                .append(String.format(NAME_FORMAT + SALARY_STR_FORMAT, NAME, SALARY))
                .append(System.lineSeparator())
                .append(String.format(NAME_FORMAT, worker.getName()))
                .append(String.format(SALARY_FORMAT, worker.getSalary()))
                .toString();
        assertThat(engine.generateForHR(em -> true), is(expect));
    }

    @Test
    public void whenGeneratedForBookkeeping() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        String expect = new StringBuilder()
                .append(String.format(NAME_FORMAT
                        + DATE_FORMAT
                        + DATE_FORMAT
                        + SALARY_STR_FORMAT, NAME, HIRED, FIRED, SALARY))
                .append(System.lineSeparator())
                .append(String.format(NAME_FORMAT, worker.getName()))
                .append(String.format(DATE_FORMAT, worker.getHired()))
                .append(String.format(DATE_FORMAT, worker.getFired()))
                .append(String.format(SALARY_FORMAT, worker.getSalary() * 1000))
                .toString();
        assertThat(engine.generateForBookkeeping(em -> true), is(expect));
    }

    @Test
    public void whenGeneratedForProgrammers() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        String separator = System.lineSeparator();
        String expect = new StringBuilder()
                .append("<html>").append(separator)
                .append(String.format(NAME_FORMAT
                        + DATE_FORMAT
                        + DATE_FORMAT
                        + SALARY_STR_FORMAT, NAME, HIRED, FIRED, SALARY))
                .append(separator)
                .append(String.format(NAME_FORMAT, worker.getName()))
                .append(String.format(DATE_FORMAT, worker.getHired()))
                .append(String.format(DATE_FORMAT, worker.getFired()))
                .append(String.format(SALARY_FORMAT, worker.getSalary()))
                .append(separator).append("</html>")
                .toString();
        assertThat(engine.generateForProgrammers(em -> true), is(expect));
    }
}