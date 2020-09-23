package ru.job4j.srp.report;

import ru.job4j.srp.Employee;
import ru.job4j.srp.convert.Converter;
import ru.job4j.srp.convert.ConverterXML;
import ru.job4j.srp.store.Store;

import java.util.List;
import java.util.function.Predicate;

public class ReportProgrammers implements Report {

    private final Converter converterXML = new ConverterXML();
    private final Store store;

    public ReportProgrammers(Store store) {
        this.store = store;
    }

    @Override
    public String createReport(Predicate<Employee> filter) {
        List<Employee> sortedList = store.findBy(filter);
        return converterXML.convert(sortedList);
    }
}
