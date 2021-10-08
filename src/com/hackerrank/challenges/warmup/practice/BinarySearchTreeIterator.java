package com.hackerrank.challenges.warmup.practice;

import java.util.Iterator;
import java.util.Stack;

public class BinarySearchTreeIterator implements Iterator<Integer> {

    private Stack<BinarySearchTree> stack;

    public BinarySearchTreeIterator(BinarySearchTree root) {
        stack = new Stack<>();

        pushAndScan(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        BinarySearchTree next = stack.pop();

        if (next.right != null) {
            pushAndScan(next.right);
        }

        return next.value;
    }

    private void pushAndScan(BinarySearchTree tree) {
        if (tree != null) {
            stack.push(tree);

            while (tree.left != null) {
                stack.push(tree.left);
                tree = tree.left;
            }

        }
    }


    static class BinarySearchTree {
        Integer value;
        BinarySearchTree left;
        BinarySearchTree right;
    }
}
