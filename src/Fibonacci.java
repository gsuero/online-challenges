import java.util.Scanner;

public class Fibonacci {
  private static long calc_fib(int n) {
      if (n <= 1)
          return n;
      long prev = 0;
      long current = 1;
      for (long a = 0; a < n-1; a++) {
          long tmp = prev;
          prev = current;
          current = tmp + current;
      }
      return current;
  }

  
  // 0 1 2 3 5 8 13 21 34 55
  public static void main(String args[]) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    System.out.println(calc_fib(n));
  }
}
