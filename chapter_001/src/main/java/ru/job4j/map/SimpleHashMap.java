package ru.job4j.map;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

public class SimpleHashMap<K, V> {

    private int size;
    private int threshold;
    private int capacity;
    private float loadFactor;
    private Node<K, V>[] table;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final int DEFAULT_CAPACITY = 16;

    public SimpleHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        this.capacity = DEFAULT_CAPACITY;
        this.table = new Node[this.capacity];
        this.size = 0;
        tableSizeFor();
    }

    private void tableSizeFor() {
        this.threshold = (int) (this.capacity * loadFactor);
    }

    static int hash(Object key) {
        return key == null ? 0 : key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private void checkThresholdAndResize() {
        if (this.size > this.threshold) {
            int oldCapacity = capacity;
            capacity = capacity * 2;
            threshold = threshold * 2;
            table = copyArrays(oldCapacity);
        }
    }

    private Node<K, V>[] copyArrays(int oldCapacity) {
        Node<K, V>[] newTable = new Node[this.capacity];
        for (int i = 0; i < oldCapacity; i++) {
            Node<K, V> node = table[i];
            if (node != null) {
                int hash = hash(node.key);
                int index = getIndex(hash);
                newTable[index] = node;
            }
        }
        return newTable;
    }

    private int getIndex(int hash) {
        int capacity = table.length;
        return (capacity - 1) & hash;
    }

    public boolean insert(K key, V value) {
        checkThresholdAndResize();
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = table[index];
        if (node == null) {
            table[index] = new Node<>(hash, key, value);
            size++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = table[index];
        if (node != null && node.hash == hash && (Objects.equals(key, node.key))) {
            return node.value;
        }
        return null;
    }

    public boolean delete(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> nodeI = table[index];
        if (nodeI.hash == hash && (Objects.equals(key, nodeI.key))) {
            table[index] = null;
            size--;
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        return "SimpleHashMap{"
                + "size=" + size
                + ", threshold=" + threshold
                + ", capacity=" + capacity
                + ", loadFactor=" + loadFactor
                + ", table=" + Arrays.toString(table)
                + '}';
    }

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return "(" + key + "=" + value + ")";
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                return Objects.equals(key, e.getKey())
                        && Objects.equals(value, e.getValue());
            }
            return false;
        }
    }
}
