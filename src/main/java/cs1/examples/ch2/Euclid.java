package cs1.examples.ch2;
/**
 * Computes the greatest common divisor of two integers.
 * 
 */
import edu.princeton.cs.algs4.StdOut;

public class Euclid {
  //recursive implementation
  public static int gcd(int p, int q) {
      if (q == 0) return p;
      else return gcd(q, p % q);
  }

  // non-recursive implementation
  public static int gcd2(int p, int q) {
      while (q != 0) {
          int temp = q;
          q = p % q;
          p = temp;
      }
      return p;
  }

  public static void main(String[] args) {
      int p = Integer.parseInt(args[0]);
      int q = Integer.parseInt(args[1]);
      int d  = gcd(p, q);
      int d2 = gcd2(p, q);
      StdOut.println("gcd(" + p + ", " + q + ") = " + d);
      StdOut.println("gcd(" + p + ", " + q + ") = " + d2);
  }
}
