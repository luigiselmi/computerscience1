package cs1.examples.ch2;

import edu.princeton.cs.algs4.StdOut;

public class LongestCommonSubsequence {

  //Compute length of LCS for all subproblems.
  public static String lcs(String x, String y) {
    
    int m = x.length(), n = y.length();
    int[][] opt = new int[m + 1][n + 1]; // substitution matrix
    
    for (int i = m-1; i >= 0; i--) {
      for (int j = n-1; j >= 0; j--) {
    	char cx = x.charAt(i);
    	char cy = y.charAt(j);
        if (cx == cy) {
            opt[i][j] = opt[i + 1][j + 1] + 1;
        }
        else {
            opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
        }
      }
    }

    // Recover LCS itself.
    String lcs = "";
    int i = 0, j = 0;
    while (i < m && j < n) {
      char cx = x.charAt(i);
      char cy = y.charAt(j);
      if (cx == cy) {
        lcs += cx;
        i++;
        j++;
      }
      else if (opt[i + 1][j] >= opt[i][j + 1]) i++;
      else j++;
    }
    return lcs;
  }

  public static void main(String[] args) {
	String s = args[0];
	String t = args[1];
    String lcs = lcs(s, t);
    StdOut.println(lcs);
  }
}
