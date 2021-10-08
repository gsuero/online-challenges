package com.hackerrank.challenges.warmup.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Duplicates {

    private static int[] getDuplicates(int[] a, int[] b) {

        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return new int[] {};
        }

        Arrays.sort(a);
        Arrays.sort(b);

        List<Integer> duplicates = new ArrayList<>();

        for (int outerIndex = 0; outerIndex < a.length; outerIndex++) {
            for (int innerIndex = 0; innerIndex < b.length; innerIndex++) {
                if (a[outerIndex] == b[innerIndex]) {
                    duplicates.add(a[outerIndex]);
                }
            }
        }

        return duplicates.stream().mapToInt(i->i).toArray();
    }
}
