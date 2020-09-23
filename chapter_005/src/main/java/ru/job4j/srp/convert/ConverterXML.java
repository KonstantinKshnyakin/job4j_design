package ru.job4j.srp.convert;

import ru.job4j.srp.Employee;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class ConverterXML implements Converter {
    @Override
    public String convert(List<Employee> employeeList) {
        StringWriter stringWriter = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            for (Employee employee : employeeList) {
                jaxbMarshaller.marshal(employee, stringWriter);
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
