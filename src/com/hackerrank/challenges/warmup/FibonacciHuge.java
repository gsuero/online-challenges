package com.hackerrank.challenges.warmup;

import java.util.*;
import java.util.stream.IntStream;

public class FibonacciHuge {
    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;
        long previous = 0;
        long current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current);
        }
        return current % m;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        long newvalue = Long.remainderUnsigned(n, 8);
        System.out.println(getFibonacciHugeNaive(newvalue, m));
    }
}

