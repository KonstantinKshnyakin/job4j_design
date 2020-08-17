package ru.job4j.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T deleteFirst() {
        checkHead();
        Node<T> oldHead = this.head;
        head = oldHead.next;
        oldHead.next = null;
        return oldHead.value;
    }

    public T deleteLast() {
        checkHead();
        Node<T> element = this.head;
        Node<T> prevElem = null;
        if (element.next == null) {
            head = null;
            return element.value;
        }
        while (element.next != null) {
                prevElem = element;
                element = element.next;
        }
        prevElem.next = null;
        return element.value;
    }

    public void revert() {
        checkHead();
        Node<T> previous = null;
        Node<T> current = this.head;
        Node<T> next;
        while ((next = current.next) != null) {
            current.next = previous;
            previous = current;
            current = next;
        }
        current.next = previous;
        this.head = current;
    }

    private void checkHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}