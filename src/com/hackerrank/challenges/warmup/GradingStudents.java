package com.hackerrank.challenges.warmup;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GradingStudents {

    /*
     * Complete the gradingStudents function below.
     */
    static int[] gradingStudents(int[] grades) {
        /*
         * Write your code here.
         */
        for (int a = 0; a < grades.length; a++) {
            grades[a] = round(grades[a]);
        }

        return grades;
    }
    static int round(int grade) {
        if (grade < 38) {
            return grade;
        }
        int steps = 0;
        for (int a = grade; a < grade + 3 ; a++, steps++) {
            if (a % 5 == 0 && steps <= 2) {
                return a;
            }
        }
        return grade;
    }
    
    //preferred
    static int round2(int grade) {
        if (grade < 38) {
            return grade;
        }
        int mod = grade % 5;
        //73 % 5  = 3
        if (mod >= 3) {
            return (5 - mod) + grade;
        }
        return grade;
        
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] grades = new int[n];

        for (int gradesItr = 0; gradesItr < n; gradesItr++) {
            int gradesItem = Integer.parseInt(scan.nextLine().trim());
            grades[gradesItr] = gradesItem;
        }

        int[] result = gradingStudents(grades);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}

