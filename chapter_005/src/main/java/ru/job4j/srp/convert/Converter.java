package ru.job4j.srp.convert;

import ru.job4j.srp.Employee;

import java.util.List;

public interface Converter {

    String convert(List<Employee> employeeList);
}
