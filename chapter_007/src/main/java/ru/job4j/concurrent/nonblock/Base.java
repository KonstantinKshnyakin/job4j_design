package ru.job4j.concurrent.nonblock;

import java.util.Objects;

public class Base {

    private static int idCounter = 0;

    private final int id;
    private int version;
    private String data;

    public Base(String data) {
        this.data = data;
        this.id = ++idCounter;
        this.version = 0;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void incrementVersion() {
        ++version;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return this.id == base.id &&
                this.version == base.version &&
                Objects.equals(this.data, base.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, data);
    }

    @Override
    public String toString() {
        return "Base{"
                + " id=" + id
                + ", version=" + version
                + ", data='" + data + '\''
                + '}';
    }
}
