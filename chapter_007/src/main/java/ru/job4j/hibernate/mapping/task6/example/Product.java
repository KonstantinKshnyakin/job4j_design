package ru.job4j.hibernate.mapping.task6.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String producer;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public static Product of(String name, String producer) {
        Product p = new Product();
        p.name = name;
        p.producer = producer;
        p.created = new Date(System.currentTimeMillis());
        return p;
    }

    // getters and setters
    // equals and hashcode
    // toString

    @Override
    public String toString() {
        return "Product{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", producer='" + producer + '\''
                + ", created=" + created
                + '}';
    }
}
