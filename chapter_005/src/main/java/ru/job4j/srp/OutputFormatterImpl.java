package ru.job4j.srp;

public class OutputFormatterImpl implements OutputFormatter {

    public static final String separator = System.lineSeparator();

    @Override
    public String convertToXML(String convertStr) {
        convertStr = "<xml>" + separator + convertStr + separator + "</xml>";
        return convertStr;
    }

    @Override
    public String convertToJson(String convertStr) {
        convertStr = "{json}" + separator + convertStr + separator + "{json}";
        return convertStr;
    }

    @Override
    public String convertToHTML(String convertStr) {
        convertStr = "<html>" + separator + convertStr + separator + "</html>";
        return convertStr;
    }
}
