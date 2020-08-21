package ru.job4j.tree;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTreeIsBinaryThenReturnTrue() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(4, 8);
        tree.add(4, 9);
        tree.add(4, 10);
        assertThat(
                tree.isBinary(),
                is(false)
        );
    }

    @Test
    public void whenTreeNotIsBinaryThenReturnFalse() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        tree.add(4, 8);
        tree.add(4, 9);
        assertThat(
                tree.isBinary(),
                is(true)
        );
    }
}