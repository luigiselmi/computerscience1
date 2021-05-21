/*
 * An implementation of a Java ArrayList based on
 * array resizing.
 * @author Luigi Selmi
 */
package datastructures.cs1;

public class MyArrayList<Item> {

  private Item[] a;
  private int N; // number of items in the array
  
  public MyArrayList(int capacity) {
    a = (Item[]) new Object[capacity];
  }
  
  // adds an item to the list
  public void add(Item item) {
    if (a.length == N) resize(2 * N); // doubles the array's capacity 
    a[N++] = item;
  }
  
  // Returns the last item
  public Item remove() {
    if (N == 0) return null;
    Item item = a[--N];
    a[N] = null; // remove the object from memory
    if ( N < a.length / 4) resize(N / 2); // halves the array's capacity
    return item;
  }
  
  // returns the number of elements
  public int size() {
    return N;
  }
  
  // changes the size of the array
  private void resize(int capacity) { 
    Item[] temp = (Item[]) new Object[capacity];
    for (int i = 0; i < N; i++)
      temp[i] = a[i];
    a = temp;
  }
  
  public static void main(String[] args) {
    MyArrayList<String> list = new MyArrayList<String>(3);
    list.add("Rome");
    list.add("Venice");
    list.add("Naples");
    System.out.println(list.size());
    
    list.add("Florence");
    System.out.println(list.size());
    
    System.out.println(list.remove());
    System.out.println(list.remove());
    System.out.println(list.remove());
    System.out.println(list.size());
  }

}
