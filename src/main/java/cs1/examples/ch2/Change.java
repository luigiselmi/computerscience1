package cs1.examples.ch2;
/**
 * Given an integer representing a given amount of money and 
 * a set of types of coins, e.g. 1 cent, 5 cents, 25 cents, 
 * computes the minimum number of coins required to make that 
 * amount.
 */
public class Change {
  
  private int[] coins = new int[]{10, 6, 1}; // coins ordered from biggest to smallest
  /**
   * Top-down (recursive) dynamic programming solution.
   * Caches the values as they are computed.
   * @param amount
   * @return total number of coins.
   */
  public int topDownMakeChange(int amount) {
    int[] cache = new int[amount + 1]; // Initialize cache with values as -1
    for (int i = 1; i < amount + 1; i++)
      cache[i] = -1;
    return topDownMakeChange(amount, cache);
  }
  private int topDownMakeChange(int amount, int [] cache) {
    // Return the value if it’s in the cache
    if (cache[amount] >= 0) return cache[amount];
    int minCoins = Integer.MAX_VALUE;
    // Find the best coin
    for (int coin : coins) {
      if (amount - coin >= 0) {
          int currMinCoins = topDownMakeChange(amount - coin, cache);
          if (currMinCoins < minCoins)
            minCoins = currMinCoins;
      }
    }
    // Save the value into the cache
    cache[amount] = minCoins + 1;
    return cache[amount];
  }
  
  /**
   * Bottom up (iterative) dynamic programming solution. 
   * Iteratively compute number of coins for
   * larger and larger amounts of change
   * @param amount
   * @return
   */
  public int bottomUpMakeChange(int amount) {
    int[] cache = new int[amount + 1];
    for (int i = 1; i <= amount; i++) {
      int minCoins = Integer.MAX_VALUE;
      //Try removing each coin from the total
      //and see which requires the fewest
      //extra coins
      for (int coin : coins) {
        if (i - coin >= 0) {
          int currCoins = cache[i-coin] + 1;
          if (currCoins < minCoins) {
            minCoins = currCoins;
          }
        }
      }
      cache[i] = minCoins;
    }
    return cache[amount];
  }

  /**
   * Brute force recursive solution. It goes 
   * through every combination of coins that 
   * sum up to amount to find the minimum number
   * of coins.
   * @param amount
   * @return total number of coins.
   */
  public int recursiveMakeChange(int amount) {
    if (amount == 0) return 0;
    int minCoins = Integer.MAX_VALUE;
    // Try removing each coin from the total and see how many more coins are required
    for (int coin : coins) {
      if (amount - coin >= 0) { // Skip a coin if it’s value is greater than the amount remaining
        int currMinCoins = recursiveMakeChange(amount - coin);
        if (currMinCoins < minCoins)
          minCoins = currMinCoins;
        }
      }
    return minCoins + 1; // Add back the coin removed recursively
  }
  public static void main(String[] args) {
    Change change = new Change();
    int changes = change.bottomUpMakeChange(12);
    System.out.println("Changes: " + changes);
  }

}
