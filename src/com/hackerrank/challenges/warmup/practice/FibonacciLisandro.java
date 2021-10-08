package com.hackerrank.challenges.warmup.practice;

public class FibonacciLisandro {

    public static void main(String[] args) {
        System.out.println("Fibonacci position for 3: " + fibonacci(3));
    }

    public static int fibonacci(int f) {
        if (f <= 1) {
            return f;
        } else if (f == 2) {
            return 1;
        }

        int previous = 0;
        int current = 1;

        for (int a = 1; a < f; a++ ) {
            int temp = current;
            current = current + previous;
            previous = temp;
        }

        return current;
    }
}
