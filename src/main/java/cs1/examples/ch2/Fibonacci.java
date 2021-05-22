package cs1.examples.ch2;
/**
 * Implements the Fibonacci algorithm.
 */
import edu.princeton.cs.algs4.StdOut;

public class Fibonacci {
  
  private static long[] f = new long[92];

  // top-down or recursive solution
  public static long fibonacci(int n) {
      if (n == 0) return 0;
      if (n == 1) return 1;

      // return cached value (if previously computed)
      if (f[n] > 0) return f[n];

      // compute and cache value
      f[n] = fibonacci(n-1) + fibonacci(n-2);
      return f[n];
  }
  // bottom-up or iterative solution
  public static long fibonacciBottomUp(int n) {
    long[] f = new long[n+1];
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= n; i++)
        f[i] = f[i-1] + f[i-2];
    return f[n];
  }

  public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      for (int i = 1; i <= n; i++)
          StdOut.println(i + ": " + fibonacciBottomUp(i));
  }
}
