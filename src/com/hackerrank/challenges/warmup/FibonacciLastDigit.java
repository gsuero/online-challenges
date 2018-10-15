package com.hackerrank.challenges.warmup;

import java.util.*;

public class FibonacciLastDigit {
    private static long getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long[] nth = new long[n];

        for (int i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % 10;
            nth[i] = current;
        }

        return current % 10;
    }
    
    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        System.out.println(getFibonacciLastDigitNaive(n));
    }
}

