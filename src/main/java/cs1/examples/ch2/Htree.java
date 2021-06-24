/**
 * Draws recursively an H-tree of order n.
 * H-trees of lower order n-1 are halved in size. 
 */
package cs1.examples.ch2;

import edu.princeton.cs.algs4.StdDraw;

public class Htree {
  
  public static void draw(int n, double size, double x, double y) { 
    if (n == 0) return;
    double x0 = x - size/2, x1 = x + size/2;
    double y0 = y - size/2, y1 = y + size/2;
    StdDraw.line(x0, y, x1, y); // horizontal branch of H
    StdDraw.line(x0, y0, x0, y1); // vertical left branch of H
    StdDraw.line(x1, y0, x1, y1); // vertical right branch of H
    draw(n-1, size/2, x0, y0); // draw a H on the upper-left corner
    draw(n-1, size/2, x0, y1); // draw a H on the bottom-left corner
    draw(n-1, size/2, x1, y0); // draw a H on the upper-right corner
    draw(n-1, size/2, x1, y1); // draw a H on the bottom-right corner
  }
  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    draw(n, 2.0, 1.0, 1.0);
  }
}
