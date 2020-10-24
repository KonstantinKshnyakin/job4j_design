package ru.job4j.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Category> categories;

    @Column(name = "is_done")
    private Boolean isDone;

    public static Item of(String description) {
        Item item = new Item();
        item.description = description;
        item.created = LocalDateTime.now();
        item.categories = new ArrayList<>();
        item.isDone = false;
        return item;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategories(List<Category> category) {
        this.categories.addAll(category);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id=" + id
                + ", description='" + description + '\''
                + ", created=" + created
                + ", category=" + categories
                + ", isDone=" + isDone
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        if (!Objects.equals(id, item.id)) {
            return false;
        }
        if (!Objects.equals(description, item.description)) {
            return false;
        }
        if (!Objects.equals(created, item.created)) {
            return false;
        }
        if (!Objects.equals(categories, item.categories)) {
            return false;
        }
        return Objects.equals(isDone, item.isDone);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (categories != null ? created.hashCode() : 0);
        result = 31 * result + (isDone != null ? isDone.hashCode() : 0);
        return result;
    }
}
