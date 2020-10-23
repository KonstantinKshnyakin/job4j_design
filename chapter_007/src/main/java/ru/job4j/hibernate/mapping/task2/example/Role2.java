package ru.job4j.hibernate.mapping.task2.example;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "j2_role")
public class Role2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User2> users = new ArrayList<>();

    public static Role2 of(String name) {
        Role2 role = new Role2();
        role.name = name;
        role.users = new ArrayList<>();
        return role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User2> getUsers() {
        return users;
    }

    public void setUsers(List<User2> users) {
        this.users = users;
    }

    public void addUser(User2 user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "Role2{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", users=" + users
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
        Role2 role = (Role2) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
