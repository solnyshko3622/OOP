package ru.nsu.yukhnina;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

/**
 * Iterator based on beep-first search algorithm.
 */
class TreeIteratorDfs<T> implements Iterator<Tree<T>> {
    private Stack<Tree<T>> stack;
    private Tree<T> current;

    TreeIteratorDfs(Tree<T> root) {
        stack = new Stack<>();
        current = findRoot(root);
        findRoot(current).setFlagIterator(true);
        if (current != null) {
            stack.push(current);
        }
    }

    @Override
    public boolean hasNext() {
        if (stack.isEmpty()) {
            findRoot(current).setFlagIterator(false);
        }
        return !stack.isEmpty();
    }

    @Override
    public Tree<T> next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more elements");
        }
        current = stack.pop();
        findRoot(current).setFlagIterator(true);
        for (Tree<T> i : current.getChildren()) {
            stack.push(i);
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
