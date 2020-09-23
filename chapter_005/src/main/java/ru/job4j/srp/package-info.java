@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
package ru.job4j.srp;

    import org.joda.time.LocalDate;
    import ru.job4j.srp.adapter.LocalDateAdapter;

    import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;