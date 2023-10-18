package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Iterator based on breath-first search algorithm.
 */
public class TreeIteratorBfs<T> implements Iterator<Tree<T>> {
    Queue<Tree<T>> queue;
    Tree<T> current;

    TreeIteratorBfs(Tree<T> root) {
        queue = new LinkedList<>();
        current = findRoot(root);
        root.setFlagIterator(true);
        if (current != null) {
            queue.offer(current);
        }
    }

    @Override
    public boolean hasNext() {
        if (queue.isEmpty()) {
            findRoot(current).setFlagIterator(false);
        }
        return !queue.isEmpty();
    }

    @Override
    public Tree<T> next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        current = queue.poll();
        assert current != null;
        for (Tree<T> child : current.getChildren()) {
            queue.offer(child);
        }
        return current;
    }

    @Override
    public void remove() {
        if (current == null) {
            throw new IllegalStateException("No element to remove");
        }
        // Remove the current node from the tree
        removeNode(current);
        current = null;
    }
    
    private void removeNode(Tree<T> node) {
        ArrayList<Tree<T>> newBaby = new ArrayList<>();
        for (Tree<T> k : node.getChildren()) {
            k.setParent(null);
        }
        Tree<T> father = node.getParent();
        for (Tree<T> c : father.getChildren()) {
            if (c != node) {
                newBaby.add(c);
            }
        }
        father.setChildren(newBaby);
    }

    private Tree<T> findRoot(Tree<T> node) {
        Tree<T> root = node;
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }
}
