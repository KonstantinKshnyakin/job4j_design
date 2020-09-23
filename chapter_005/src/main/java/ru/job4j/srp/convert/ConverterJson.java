package ru.job4j.srp.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import ru.job4j.srp.Employee;

import java.util.List;

public class ConverterJson implements Converter {
    @Override
    public String convert(List<Employee> employeeList) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        String string = "";
        for (Employee employee : employeeList) {
            try {
                string = mapper.writeValueAsString(employee);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return string;
    }
}
