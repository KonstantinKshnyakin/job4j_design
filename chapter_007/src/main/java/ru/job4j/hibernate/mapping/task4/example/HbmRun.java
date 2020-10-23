package ru.job4j.hibernate.mapping.task4.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HbmRun {
    public static void main(String[] args) {
        printCategory();
//        saveCategory();
    }

    private static void saveCategory() {
        transaction(
                session -> {
                    Category category = Category.of("Category1");
                    Task task1 = Task.of("Description1", category);
                    Task task2 = Task.of("Description2", category);
                    Task task3 = Task.of("Description3", category);
                    category.addTask(task1);
                    category.addTask(task2);
                    category.addTask(task3);
                    session.persist(category);
                    return null;
                }
        );


    }

    private static void printCategory() {
        List<Category> list = transaction(
                session -> session.createQuery(
                        "select distinct c from Category c join fetch c.tasks",
                        Category.class
                ).list()
        );

        for (Category category : list) {
            for (Task task : category.getTasks()) {
                System.out.println(task);
            }
        }
    }

    private static List<Category> transaction(Function<Session, List<Category>> function) {
        List<Category> categories = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            categories = function.apply(session);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
        return categories;
    }
}
