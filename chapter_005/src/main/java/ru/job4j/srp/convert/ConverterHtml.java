package ru.job4j.srp.convert;

import ru.job4j.srp.Employee;

import java.lang.reflect.Field;
import java.util.List;

public class ConverterHtml implements Converter {
    @Override
    public String convert(List<Employee> employeeList) {
        StringBuilder text = new StringBuilder();
        String sep = System.lineSeparator();
        text.append("<table>").append(sep);
        String title = getTitle();
        text.append(title);
        for (Employee employee : employeeList) {
            text.append("<tr>").append(sep)
                    .append("<td>").append(employee.getName()).append("</td>")
                    .append("<td>").append(employee.getHired()).append("</td>")
                    .append("<td>").append(employee.getFired()).append("</td>")
                    .append("<td>").append(employee.getSalary()).append("</td>")
                    .append("</tr>");
        }
        text.append("</table>");
        return text.toString();
    }

    private String getTitle() {
        StringBuilder text = new StringBuilder();
        Field[] declaredFields = Employee.class.getDeclaredFields();
        String sep = System.lineSeparator();
        text.append("<tr>").append(sep);
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String str = declaredField.toString();
            int index = str.lastIndexOf('.');
            String substring = str.substring(index + 1);
            text.append("<th>").append(substring).append("</th>").append(sep);
        }
        text.append("</tr>").append(sep);
        return text.toString();
    }
}
