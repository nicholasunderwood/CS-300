//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinekedNode.java
// Course:   CS 300 Fall 2020
//
// Author:   Nicholas Underwood
// Email:    ndunderwood@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// N/A
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A singly linked node of a gernic data type
 */
class LinkedNode<T> {
  private T data;
  private LinkedNode<T> next;

  /**
   * constructs a new linked node with a null tail
   * 
   * @param data data
   */
  public LinkedNode(T data) {
    this(data, null);
  }

  /**
   * constructs a new linked node
   * 
   * @param data data
   * @param next node to follow
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }

  /**
   * @return the next node
   */
  public LinkedNode<T> getNext() {
    return next;
  }

  /**
   * @return the node's data
   */
  public T getData() {
    return data;
  }

  /**
   * @return the string representation of the data
   */
  public String toString() {
    return data.toString();
  }

  /**
   * sets the next node to a new node
   * 
   * @param next the new node
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}