/**
 * A Priority queue is a heap-ordered complete binary tree.
 * A binary tree is heap-ordered when the key in 
 * each node is equal or larger than its children’s keys. 
 * A priority queue provides two main APIs:
 * 
 * - insert
 * - remove the maximum
 * 
 * Both operations require a number of comparisons
 * proportional to log(n), where n is the number of
 * elements.
 * 
 * @author Luigi Selmi
 */
package cs1.datastructures;

public class MyPriorityQueue<Key extends Comparable<Key>> {
  
  private Key[] pq; // store items at indices 1 to n
  private int n; // number of items on priority queue
  
  public MyPriorityQueue(int initCapacity) {
    pq = (Key[]) new Comparable[initCapacity + 1];
    n = 0;
  }
  
  /*
   * Insert a key at the end of the heap (array) and increment 
   * its size. Then swim to the top if the parent key is smaller
   * to keep the binary tree heap-ordered.
   */
  public void insert(Key v) {
    if (pq.length == n) resize(2 * n); // doubles the array's capacity
    pq[++n] = v;
    swim(n);
  }
  
  /*
   * Remove the root (maximum) of the binary tree. Then
   * move the last key to the top (root). Decrement the heap size
   * and then sink from the root to the bottom till no child
   * is smaller.
   */
  public Key delMax() {
    Key max = pq[1]; // Retrieve max key from top.
    exch(1, n--); // Exchange with last item.
    pq[n + 1] = null; // Avoid loitering.
    sink(1); // Restore heap property.
    if ( n < pq.length / 4) resize(n / 2); // halves the array's capacity
    return max;
  }
  
  public Key getMax() {
    return pq[1];
  }
  
  public Key [] maxChildren() {
    Key [] maxChildren = (Key[]) new Comparable[2];
    maxChildren[0] = pq[2];
    maxChildren[1] = pq[3];
    return maxChildren;
  }

  /* Used to navigate bottom-up the binary tree 
  * to keep it heap-ordered. If the index of a node
  * is k, the index of its parent node is k / 2.
  * If the parent node's key is less than the child's
  * key they have to be exchanged.
  */
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k/2, k); // k/2 parent node index
      k = k/2;
    }
  }
  
  /*
   * Used to navigate top-down the binary tree
   * to keep it heap-ordered. If the index of a node is k
   * the indexes of its children nodes are 2k and 2k + 1.
   * If the node's key is less than any of its two children
   * the key must be exchanged with the larger one.
   */
  private void sink(int k) {
    while (2*k <= n) {
      int j = 2*k; // left child node index
      if (j < n && less(j, j+1)) j++; // choose larger child
      if (!less(k, j)) break; // exit if the parent is not smaller than the child
      exch(k, j); // exchange the keys between parent and larger child
      k = j;
    }
  }

  /* Used to check if a node's key is less than
  * its parent's key. In that case it must be exchanged
  * to keep the binary tree heap-ordered. 
  */
  private boolean less(int i, int j) { 
    return pq[i].compareTo(pq[j]) < 0; 
  }
  
  /* Used to exchange a node's key with its parent's
  * key when it's larger, to keep the binary tree heap-ordered.
  */
  private void exch(int i, int j) { 
    Key t = pq[i]; 
    pq[i] = pq[j]; 
    pq[j] = t; 
  }
  
  //changes the size of the array
  private void resize(int capacity) { 
    Key[] temp = (Key[]) new Comparable[capacity];
    for (int i = 0; i < n; i++)
      temp[i] = pq[i];
    pq = temp;
  }

  public static void main(String[] args) {
    char [] data = {'T', 'N', 'R', 'P', 'O', 'A', 'E', 'I', 'H', 'G'};
    MyPriorityQueue mypq = new MyPriorityQueue(32);
    for(char l: data) {
      mypq.insert(l);
    }
    System.out.println(mypq.getMax());
    System.out.println(mypq.maxChildren()[0] + ", " +  mypq.maxChildren()[1]);
    mypq.insert('S');
    System.out.println(mypq.getMax());
    System.out.println(mypq.maxChildren()[0] + ", " +  mypq.maxChildren()[1]);
    mypq.delMax();
    System.out.println(mypq.getMax());
    System.out.println(mypq.maxChildren()[0] + ", " +  mypq.maxChildren()[1]);
  }

}
