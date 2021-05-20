/**
 * Implements a FIFO collection.
 * The order is preserved. It is based on a linked list.
 */
package datastructures.cs1;

public class MyQueue<Item> {
  
  private Node<Item> first;
  private Node<Item> last;
  private int numItems;
 
  private class Node<Item> {
    private Item item;
    private Node<Item> next;
  }
  
  public boolean isEmpty() {
    return numItems == 0;
  }
  
  public int size() {
    return numItems;
  }
  
  //adds the item to the end of the linked list
  public void enqueue(Item item) {
    Node<Item> oldlast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) 
      first = last;
    else 
      oldlast.next = last;
    numItems++;
  }
  
  //removes an item from the beginning of the linked list.
  public Item dequeue() {
    Item item = first.item;
    first = first.next;
    numItems--;
    return item;
  }
  
  public static void main(String[] args) {
    MyQueue<String> tasks = new MyQueue<String>();
    tasks.enqueue("1st task");
    tasks.enqueue("2nd task");
    tasks.enqueue("3rd task");
    System.out.println(tasks.size());
    
    while(!tasks.isEmpty())
      System.out.println(tasks.dequeue());

  }

}
