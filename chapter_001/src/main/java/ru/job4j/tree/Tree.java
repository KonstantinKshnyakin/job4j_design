package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
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

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
