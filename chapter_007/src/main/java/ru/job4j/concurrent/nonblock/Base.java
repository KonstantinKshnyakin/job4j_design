package ru.job4j.concurrent.nonblock;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Base {

    private final static AtomicInteger ID_COUNTER = new AtomicInteger();
    private final AtomicInteger versionCounter = new AtomicInteger();

    private final int id;
    private int version;
    private String data;

    public Base(String data) {
        this.data = data;
        this.id = ID_COUNTER.incrementAndGet();
        this.version = versionCounter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void incrementVersion() {
        this.version = versionCounter.incrementAndGet();
    }

    public String getData() {
        return data;
    }

    public synchronized void setData(String data) {
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
                Objects.equals(this.versionCounter, base.versionCounter) &&
                Objects.equals(this.data, base.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionCounter, id, version, data);
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
