package com.hackerrank.challenges.warmup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class HackerRankWarmUp {

    public static void main(String[] args) {

        System.out.println(timeConversion("07:05:45PM"));
    }
    static SimpleDateFormat format = new SimpleDateFormat("hh:mm:ssa");
    static SimpleDateFormat format24 = new SimpleDateFormat("HH:mm:ss");
    static String timeConversion(String s) {
        try {
            Date date = format.parse(s);
            return format24.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";

    }
    
    static int birthdayCakeCandles(int[] ar) {
        int blows = 0;
        int temp = 0;
        for (int a = 0; a < ar.length; a++) {
            if (temp < ar[a]) {
                temp = ar[a];
                blows = 1;
            } else if (temp == ar[a]) {
                blows++;
            }
        }
        return blows;

    }
    
    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        
        long minimun = 0;
        long maximun = 0;
        
        for (int a = 0; a < arr.length - 1; a++)
            minimun += arr[a];
        
        for (int a = arr.length - 1; a > 0; a--)
            maximun += arr[a];

        System.out.print(minimun);
        System.out.print(" ");
        System.out.print(maximun);
    }
    
    static void staircase(int n) {
        for (int a = 0; a < n; a++) {
            System.out.print(String.format("%" + (n - a) +"s", "#"));
            int left = a;
            while(left > 0) { 
                System.out.print("#");
                left--;
            }
            
            System.out.println();
        }

    }
    
    
    static void plusMinus(int[] arr) {
        int positives = 0;
        int negatives = 0;
        int zeros = 0;
        for(int a=0; a < arr.length; a++) {
            if (arr[a] > 0) {
                positives++;
            } else if (arr[a] < 0) {
                negatives++;
            } else {
                zeros++;
            }
        }
        System.out.printf("%.06f %n", getFraction(positives, arr.length));
        System.out.printf("%.06f %n", getFraction(negatives, arr.length));
        System.out.printf("%.06f %n", getFraction(zeros, arr.length));


    }
    private static double getFraction(int dividend, int divisor) {
        double ret = (double) dividend / (double) divisor;
        return ret;
    }
}
