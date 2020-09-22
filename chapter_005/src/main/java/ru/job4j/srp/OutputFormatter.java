package ru.job4j.srp;

public interface OutputFormatter {

    String convertToXML(String convertStr);

    String convertToJson(String convertStr);

    String convertToHTML(String convertStr);
}
