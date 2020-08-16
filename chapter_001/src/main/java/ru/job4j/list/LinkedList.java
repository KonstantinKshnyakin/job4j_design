package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<E> implements Iterable<E> {

    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public LinkedList() {
    }

    public void add(E value) {
        Node<E> localLast = last;
        Node<E> localNew = new Node<>(localLast, value, null);
        last = localNew;
        if (localLast == null) {
            first = localNew;
        } else {
            localLast.next = localNew;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return node(index).item;
    }

    private Node<E> node(int index) {
        Node<E> x;
        if (index < (size / 2.0)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor = 0;
            private int expectedModCount = modCount;
            private Node<E> element;


            @Override
            public boolean hasNext() {
                checkModification();
                return cursor < size;
            }

            @Override
            public E next() {
                checkModification();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (element == null) {
                    element = LinkedList.this.first;
                    return element.item;
                }
                element = element.next;
                return element.item;
            }

            private void checkModification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<E> {
        Node<E> prev;
        E item;
        Node<E> next;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }
}
