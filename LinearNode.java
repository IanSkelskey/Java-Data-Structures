/** 
 * I included the LinearNode<Item> class provided in git.
 * added functionality to interact with previous elements in list
 * so it now handles doubly linked lists
 *
 * Represents a node in a doubly linked list.
 * 
 * @author Ian Skelskey, Lewis et al., Acuna
 * @version 4.1  
 * @param <Item> contained type
 */

public class LinearNode<Item> {
  private LinearNode<Item> prev;
  private LinearNode<Item> next;
  private Item element;

  /**
   * Creates a node storing the specified element.
   * 
   * @param elem element to be stored
   */
  public LinearNode(Item elem) {
    prev = null;
    next = null;
    element = elem;
  }

  /**
   * Returns the node that precedes this one.
   * 
   * @return reference to previous node
   */
  public LinearNode<Item> getPrev() {
    return prev;
  }

  /**
   * Returns the node that follows this one.
   * 
   * @return reference to next node
   */
  public LinearNode<Item> getNext() {
    return next;
  }

  /**
   * Sets the node that precedes this one.
   * 
   * @param node node to precede this one
   */
  public void setPrev(LinearNode<Item> node) {
    prev = node;
  }

  /**
   * Sets the node that follows this one.
   * 
   * @param node node to follow this one
   */
  public void setNext(LinearNode<Item> node) {
    next = node;
  }

  /**
   * Returns the element stored in this node.
   * 
   * @return element stored at the node
   */
  public Item getElement() {
    return element;
  }

  /**
   * Sets the element stored in this node.
   * 
   * @param elem element to be stored at this node
   */
  public void setElement(Item elem) {
    element = elem;
  }
}