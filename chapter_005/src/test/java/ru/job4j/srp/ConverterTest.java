package ru.job4j.srp;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.srp.convert.Converter;
import ru.job4j.srp.convert.ConverterHtml;
import ru.job4j.srp.convert.ConverterJson;
import ru.job4j.srp.convert.ConverterXML;
import ru.job4j.srp.store.MemStore;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {

    private List<Employee> employees;

    @Before
    public void setup() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.parse("2020-09-23");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        employees = store.findBy(em -> true);
    }

    @Test
    public void whenGeneratedJson() {
        Converter converterJson = new ConverterJson();
        String convert = converterJson.convert(employees);
        String expected = "{\"name\":\"Ivan\",\"hired\":[2020,9,23],\"fired\":[2020,9,23],\"salary\":100.0}";
        assertThat(convert, is(expected));
    }

    @Test
    public void whenGeneratedXML() {
        Converter converterXML = new ConverterXML();
        String convert = converterXML.convert(employees);
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
                + "<employee>\n"
                + "    <name>Ivan</name>\n"
                + "    <hired>2020-09-23</hired>\n"
                + "    <fired>2020-09-23</fired>\n"
                + "    <salary>100.0</salary>\n"
                + "</employee>\n";
        assertThat(convert, is(expected));
    }

    @Test
    public void whenGeneratedHTML() {
        Converter converterHtml = new ConverterHtml();
        String convert = converterHtml.convert(employees);
        String expected = "<table>\r\n"
                + "<tr>\r\n"
                + "<th>name</th>\r\n"
                + "<th>hired</th>\r\n"
                + "<th>fired</th>\r\n"
                + "<th>salary</th>\r\n"
                + "</tr>\r\n"
                + "<tr>\r\n"
                + "<td>Ivan</td><td>2020-09-23</td><td>2020-09-23</td><td>100.0</td></tr></table>";
        assertThat(convert, is(expected));
    }
}
