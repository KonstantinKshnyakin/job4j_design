package ru.job4j.concurrent.nonblock;

import java.util.Objects;

public class Base {

    private final int id;
    private int version;
    private String data;

    public Base(int id, int version, String data) {
        this.id = id;
        this.version = version;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
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
        return this.id == base.id
                && this.version == base.version
                && Objects.equals(this.data, base.data);
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
