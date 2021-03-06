package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        if (findBy(child).isPresent()) {
            return false;
        }
        if (Objects.equals(parent, root.value)) {
            root.children.add(new Node<>(child));
            return true;
        } else {
            Optional<Node<E>> findParent = findBy(parent);
            if (findParent.isPresent()) {
                findParent.get().children.add(new Node<>(child));
                return true;
            }
            return false;
        }
    }

    public boolean isBinary() {
        Node<E> node = findByPredicate(eNode -> eNode.children.size() > 2);
        return node == null;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Node<E> node = findByPredicate(eNode -> eNode.value.equals(value));
        if (node != null) {
            return Optional.of(node);
        }
        return rsl;
    }

    private Node<E> findByPredicate(Predicate<Node<E>> predicate) {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                return el;
            }
            data.addAll(el.children);
        }
        return null;
    }
}
