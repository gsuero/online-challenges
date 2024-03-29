package com.hackerrank.challenges.implementation;

import java.io.*;
import java.util.*;

public class Kangaroo {

    static String kangaroo(int x1, int v1, int x2, int v2) {
        if ((x1 - x2) % (v2 - v1) == 0) {
            return "YES";
        }

        return "NO";
    }


    /*
           int cycle = x1 > x2 ? x1 : x2;

        if ((x2 > x1 && v2 > v1) || (x1 > x2 && v1 > v2)) {
            return "NO";
        }
        while (cycle <= Integer.MAX_VALUE) {

            x1 += v1;
            x2 += v2;

            if (x1 == x2)
                return "YES";

            ++cycle;
        }

        return "NO";
     */
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        String[] x1V1X2V2 = scanner.nextLine().split(" ");

        int x1 = Integer.parseInt(x1V1X2V2[0]);

        int v1 = Integer.parseInt(x1V1X2V2[1]);

        int x2 = Integer.parseInt(x1V1X2V2[2]);

        int v2 = Integer.parseInt(x1V1X2V2[3]);

        String result = kangaroo(x1, v1, x2, v2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
