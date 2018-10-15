package com.hackerrank.challenges.warmup;

import java.math.BigInteger;
import java.util.*;

public class LCM {
  private static long lcm_naive(int a, int b) {
    return (long) a * b /  gcd(a, b);
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();
    System.out.println(lcm_naive(a, b));
  }
  
  public static long gcd(long p, long q) {
      if (q == 0) return p;
      if (p == 0) return q;

      // p and q even
      if ((p & 1) == 0 && (q & 1) == 0) return gcd(p >> 1, q >> 1) << 1;

      // p is even, q is odd
      else if ((p & 1) == 0) return gcd(p >> 1, q);

      // p is odd, q is even
      else if ((q & 1) == 0) return gcd(p, q >> 1);

      // p and q odd, p >= q
      else if (p >= q) return gcd((p-q) >> 1, q);

      // p and q odd, p < q
      else return gcd(p, (q-p) >> 1);
  }
}

/*

28851538 1183019
1933053046


2000000000 1999999999
3999999998000000000
*/