package ru.job4j.srp;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.srp.report.*;
import ru.job4j.srp.store.MemStore;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class ReportEngineTest {

    public static final String NAME = "Name";
    public static final String HIRED = "Hired";
    public static final String FIRED = "Fired";
    public static final String SALARY = "Salary";
    public static final String NAME_FORMAT = "%-12s";
    public static final String DATE_FORMAT = "%-12s";
    public static final String SALARY_FORMAT = "%-7.2f";
    public static final String SALARY_STR_FORMAT = "%-7s";

    private MemStore store;
    private Employee worker;

    @Before
    public void setup() {
        store = new MemStore();
        LocalDate now = LocalDate.parse("2020-09-23");
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
    }

    @Test
    public void whenOldGenerated() {
        ReportEngine engine = new ReportEngine(store);
        String expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator()).toString();
        String generate = engine.generate(em -> true);
        assertThat(generate, is(expect));
    }

    @Test
    public void whenGeneratedForHR() {
        Report engine = new ReportHR(store);
        String expect = new StringBuilder()
                .append(String.format(NAME_FORMAT + SALARY_STR_FORMAT, NAME, SALARY))
                .append(System.lineSeparator())
                .append(String.format(NAME_FORMAT, worker.getName()))
                .append(String.format(SALARY_FORMAT, worker.getSalary()))
                .toString();
        assertThat(engine.createReport(em -> true), is(expect));
    }

    @Test
    public void whenGeneratedForBookkeeping() {
        Report engine = new ReportBookkeeping(store);
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
        assertThat(engine.createReport(em -> true), is(expect));
    }

    @Test
    public void whenGeneratedForProgrammers() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportProgrammers(store);
        String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Ivan</name>\n"
                + "    <hired>2020-09-23</hired>\n"
                + "    <fired>2020-09-23</fired>\n"
                + "    <salary>100.0</salary>\n"
                + "</employee>\n";
        String report = engine.createReport(em -> true);
        assertThat(report, is(expect));
    }
}