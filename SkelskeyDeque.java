/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 * 
 * A deque is similar to a queue with less restriction. 
 * Items can be added or removed to either end.
 * It does not require LIFO or FIFO ordering.
 * 
 * @author Ian Skelskey, Acuna
 * @version 1.0
 */
import java.util.NoSuchElementException;

public class SkelskeyDeque<Item> implements Deque<Item> {
  // Member variables
  private LinearNode<Item> head;
  private LinearNode<Item> tail;
  private int size;

  //Default Constructor
  public SkelskeyDeque() {
    //Initialize members
    head = null;
    tail = null;
    size = 0;
  }

  @Override
  public void enqueueFront(Item element) {
    //Stores input element into a Node
    LinearNode<Item> node = new LinearNode<Item>(element);
    if (head == null) {
      //Head and tail are the same in single-element list.
      head = node;
      tail = node;
    } else {
      //Input node is new head.
      node.setNext(head);
      head.setPrev(node);
      head = node;
    }
    size++;
  }

  @Override
  public void enqueueBack(Item element) {
    //Stores input element into a Node
    LinearNode<Item> node = new LinearNode<Item>(element);
    if (tail == null) {
      //Head and tail are the same in single-element list.
      head = node;
      tail = node;
    } else {
      //Input node is new tail.
      tail.setNext(node);
      node.setPrev(tail);
      tail = node;
    }
    size++;
  }

  @Override
  public Item dequeueFront() throws NoSuchElementException {
    if (head == null) {
      //For an empty list
      throw new NoSuchElementException("Deque is empty.");
    }
    Item item = head.getElement();
    head = head.getNext();

    if (head == null) {
      tail = null;
    } else {
      //removes previous element
      head.setPrev(null);
    }
    size--;

    return item;
  }

  @Override
  public Item dequeueBack() throws NoSuchElementException {
    if (tail == null) {
      throw new NoSuchElementException("Deque is empty.");
    }
    Item item = tail.getElement();
    tail = tail.getPrev();
    if (tail == null) {
      head = null;
    } else {
      //removes next element
      tail.setNext(null);
    }
    size--;
    return item;
  }

  @Override
  public Item first() throws NoSuchElementException {
    if (head ==  null){
      throw new NoSuchElementException("Deque is empty.");
    }
    return head.getElement();
  }

  @Override
  public Item last() throws NoSuchElementException {
    if (tail == null) {
      throw new NoSuchElementException("Deque is empty.");
    }
    return tail.getElement();
  }

  @Override
  public boolean isEmpty() {
    if (head == null || tail == null) {
      return true;
    }
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public String toString() {
    String result = "";

    if (isEmpty()) {
      result = "empty";

    } else {

      LinearNode<Item> node = tail;

      while (node != null) {
        result += node.getElement() + " ";
        node = node.getPrev();
      }
    }

    return result;
  }

}

