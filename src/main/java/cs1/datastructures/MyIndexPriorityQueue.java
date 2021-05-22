/**
 * An index priority queue is a heap-ordered complete binary tree
 * in which each element is associated with a unique integer, or index.
 * An integer array pq is used to store the index associated to each 
 * element heap-ordered. The elements of the index priority queue (aka keys) 
 * are stored in a different array. Another integer array qp is used to 
 * store the position of an element's index in the heap. A binary tree is 
 * heap-ordered when the key in each node is equal or larger than its 
 * children’s keys. An index priority queue provides two main APIs:
 * 
 * - insert a key (with its unique index)
 * - remove the maximum key
 * 
 * Both operations require a number of comparisons
 * proportional to log(n), where n is the number of
 * elements.
 * 
 * @author Luigi Selmi
 */
package cs1.datastructures;


public class MyIndexPriorityQueue<Key extends Comparable<Key>> {
  
  private int [] pq; // stores items' indexes in the heap (according to the keys total order)
  private Key[] keys; // key values
  private int[] qp; // stores the position of a key's index in the heap, inverse of pq: qp[pq[i]] = pq[qp[i]] = i
  private int n; // number of items on priority queue
  
  public MyIndexPriorityQueue(int maxN) {
    if (maxN < 0) throw new IllegalArgumentException("The capacity cannot be negative");
    keys = (Key[]) new Comparable[maxN + 1];
    pq = new int[maxN + 1];
    qp = new int[maxN + 1];
    for (int i = 0; i <= maxN; i++) qp[i] = -1; 
  }
  
  /*
   * Inserts a key and associates it with a unique integer index
   * that is added to the array containing the heap-ordered binary
   * tree. After the insertion a bottom-up re-ordering of the tree
   * is performed to keep it heap-ordered. The ordering depends on 
   * the comparisons between the key values (not between their indexes). 
   */
  public void insert(int index, Key key) {
    if (contains(index)) throw new IllegalArgumentException("An index must be unique.");
    n++;
    qp[index] = n;
    pq[n] = index;
    keys[index] = key;
    swim(n);
  }
  
  public boolean isEmpty() { return n == 0; }
  
  /*
   * Removes the max key from the heap. Then moves the last key 
   * to the top (root). Decrement the heap size and then sink from 
   * the root to the bottom till no child is smaller.
   */
  public int delMax() {
    int maxIndex = pq[1]; // retrieve the index of the max key from top.
    exch(1, n--); // exchange the max key with last key.
    sink(1); // restore heap order.
    keys[pq[n + 1]] = null; // avoids loitering.
    qp[pq[n + 1]] = -1;
    return maxIndex;
  }
  
  //returns true if the index exists
  private boolean contains(int index) {
    return qp[index] != - 1;
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
  * to keep the index binary tree heap-ordered. 
  */
  private boolean less(int i, int j) { 
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0; 
  }
  
  /* Used to exchange a node's index with its parent's
  * index when its key is larger, to keep the binary tree heap-ordered.
  */
  private void exch(int i, int j) { 
    int tempIndex = pq[i]; 
    pq[i] = pq[j]; 
    pq[j] = tempIndex;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
  }
  
  public static void main(String[] args) {
    String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };
    MyIndexPriorityQueue<String> ipq = new MyIndexPriorityQueue<String>(strings.length);
    for (int i = 0; i < strings.length; i++) {
        ipq.insert(i, strings[i]);
    }
    // deletes and prints each key in alphabetical order with its original index
    while (!ipq.isEmpty()) {
        int index = ipq.delMax();
        System.out.println("string[" + index + "] = " + strings[index]);
    }
  }

}
