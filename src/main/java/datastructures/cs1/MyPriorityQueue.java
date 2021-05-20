/**
 * A Priority queue is a heap-ordered binary tree that 
 * provides two main APIs:
 * - insert
 * - remove the maximum
 * @author Luigi Selmi
 */
package datastructures.cs1;

public class MyPriorityQueue<Key extends Comparable<Key>> {
  
  private Key[] pq; // store items at indices 1 to n
  private int n; // number of items on priority queue
  
  public MyPriorityQueue(int initCapacity) {
    pq = (Key[]) new Comparable[initCapacity + 1];
    n = 0;
  }
  
  /* Used to navigate the binary tree bottom-up 
  * to keep it heap-ordered. If the index of a node
  * is k, the index of its parent node is k / 2.
  */
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k/2, k);
      k = k/2;
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

  public static void main(String[] args) {
    

  }

}
