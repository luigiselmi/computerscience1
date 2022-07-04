/*
 * This class contains the implementation of a function to compute
 * the trinomial coefficients. The function has two arguments: the
 * exponent n of the trinomial and the index k of the coefficient 
 * to be returned. Given the trinomial 
 * 
 * (1 + x + x^2)^n
 * 
 * the function computes the coefficients of the expanded trinomial.
 * The number of elements of the expanded trinomial is n * 2 + 1.
 * The coefficients of the member of the expanded trinomial are 
 * symmetrical. For example for n = 3 the trinomial 
 * 
 * (1 + x + x^2)^3
 * 
 * becomes 
 * 
 * 1*x^0 + 3*x^1 + 6*x^2 + 7*x^3 + 6*x^4 + 3*x^5 + 1*x^6
 * 
 * or
 * 
 * 1 + 3*x + 6*x^2 + 7*x^3 + 6*x^4 + 3*x^5 + 1*x^6
 * 
 * and the coefficients are 
 * 
 * 1 3 6 7 6 3 1
 * 
 * If we index the coefficients using a variable -n <= k <= n 
 * we can store the values in an array. For n = 2 we need an array
 * of 2*2 + 1 = 5 elements
 * 
 * c[n = 2, k = -2] = 1
 * c[n = 2, k = -1] = 2
 * c[n = 2, k = 0]  = 3
 * c[n = 2, k = 1]  = 2
 * c[n = 2, k = 2]  = 1
 * 
 * We can compute the coefficients for n = 3 by using using the 
 * coefficients of the trinomial with exponent n - 1 and the rule
 * 
 * c[n, k] = c[n - 1, k - 1] + c[n - 1, k] + c[n - 1, k + 1]
 * 
 * assuming c[n - 1, k] = 0 if k < -(n - 1) or k > n - 1
 * 
 * For n = 3 the rule is
 * 
 * c[3, k] = c[2, k - 1] + c[2, k] + c[2, k + 1]
 * 
 * and for -2 <= k <= 2 we have
 * 
 * c[3, -3] = c[2, -4] + c[2, -3] + c[2, -2] = 0 + 0 + 1 = 1
 * c[3, -2] = c[2, -3] + c[2, -2] + c[2, -1] = 0 + 1 + 2 = 3
 * c[3, -1] = c[2, -2] + c[2, -1] + c[2, 0]  = 1 + 2 + 3 = 6
 * c[3, 0]  = c[2, -1] + c[2, 0] +  c[2, 1]  = 2 + 3 + 2 = 7
 * c[3, 1]  = c[2, 0]  + c[2, 1] + c[2, 2]   = 3 + 2 + 1 = 6 
 * c[3, 2]  = c[2, 1]  + c[2, 2] + c[2, 3]   = 2 + 1 + 0 = 3
 * c[3, 3]  = c[2, 2]  + c[2, 3] + c[2, 4]   = 1 + 0 + 0 = 1
 *  
 * So if n = 3 we need to store the coefficients for n = 2 in 
 * an array of size (n - 1) * 2 + 1 
 */
package assignments.week6;

public class TrinomialDP {
	
	// Returns the trinomial coefficient T(n, k).
	private static long trinomial(int N, int K) {
		long [] p = new long[1];
		p[0] = 1;
		if (N == 0 && K == 0) {
  		return 1;
  	}
  	else if (K < -N || K > N) {
  		 return 0;
  	}
  	else {
			for (int n = 1; n <= N; n++) {
				int size = 2 * n + 1;
				long [] c = new long [size];
				for (int m = 0; m < size; m++) {
					for (int k = -2; k <= 0; k++) {
						int j = k + m;
						if (j == 0) {
							c[j + m] = sum(p, j, j + m);
						}
					}
				}
				p = c;
			}
  	}
		
    return p[2 * K + 1];  
  }
	
	private static long sum(long [] p, int start, int end) {
		long s = 0;
		for (int i = start; i < end; i++)
			s += p[i];
		return s;	
	}
	  
  // Takes two integer arguments n and k and prints T(n, k).
  public static void main(String[] args) {
  	int n = Integer.parseInt(args[0]);
	  int k = Integer.parseInt(args[1]);
    long T = TrinomialDP.trinomial(n, k);
    System.out.println(T);
	}

}
