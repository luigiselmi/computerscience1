/*
 * An implementation of a stack (LIFO) based on
 * linked list.
 * @author Luigi Selmi
 */
package cs1.datastructures;

public class MyStack<Item> {
      
  private Node<Item> first;
  private int numItems;
 
  private class Node<Item> {
    private Item item;
    private Node<Item> next;
  }
  
  // inserts an item at the beginning 
  public void append(Item item) {
    Node<Item> oldHead = first;
    first = new Node<Item>();
    first.item = item;
    first.next = oldHead;
    numItems++;
  }
  
  public int size() {
    return numItems;
  }
  
  // removes an item from the beginning
  public Item remove() {
    Node<Item> oldFirst = first;
    first = first.next;
    numItems--;
    return oldFirst.item;
  }
  
  public boolean isEmpty() {
    return first == null;
  }
  
  public static void main(String [] args) {
    MyStack<String> mysongs = new MyStack<String>();
    mysongs.append("1st song");
    mysongs.append("2nd song");
    mysongs.append("3rd song");
    
    while(!mysongs.isEmpty())
      System.out.println(mysongs.remove());
    
    System.out.println(mysongs.size());
  }

}