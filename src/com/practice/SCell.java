package com.practice;

import java.util.Stack;

public class SCell<T extends Comparable<? super T>> {
    private final T value;
    private final SCell<T> next;

    public SCell(T value, SCell<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public SCell<T> getNext() {
        return next;
    }




    public static SCell<Long> insertIntoOrdered(SCell<Long> head, long value) {

        if (head == null || head.value > value) {
            return new SCell<>(value, head);
        }

        return new SCell<>(head.value, insertIntoOrdered(head.next, value));
    }


    public static void main(String args[]) {


        SCell<Long> cells = SCell.insertIntoOrdered(null, 1);


        cells = SCell.insertIntoOrdered(cells, 2);


        cells = SCell.insertIntoOrdered(cells, 4);


        cells = SCell.insertIntoOrdered(cells, 5);


        cells = SCell.insertIntoOrdered(cells, 3);





    }
}