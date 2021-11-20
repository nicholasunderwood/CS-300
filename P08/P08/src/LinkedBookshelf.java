//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedBookShelf.java
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
 * A bookshelf with a singly linked list of books
 */
public class LinkedBookshelf {
  private LinkedNode<Book> front;
  private LinkedNode<Book> back;
  private int size;
  private Attribute sortedBy;

  /**
   * Constructed an empty linked list with a default attribute of ID
   */
  public LinkedBookshelf() {
    this.front = null;
    this.back = null;
    this.size = 0;
    this.sortedBy = Attribute.ID;
  }

  /**
   * accessor for the number of elements in the list
   * 
   * @return the number of elements in the list
   */
  public int size() {
    return size;
  }

  /**
   * checks if the list is empty
   * 
   * @return true if the list is empty otherwise false
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * returns a string reprentation of each element in the list
   */
  public String toString() {
    String str = sortedBy.toString();
    LinkedNode<Book> curNode = front;

    while (curNode != null) {
      str += "\n" + curNode.toString();
      curNode = curNode.getNext();
    }

    return str.strip();
  }

  /**
   * returns the node at the provided index
   * 
   * @param index index of wanted node
   * @return node at index
   */
  public LinkedNode<Book> getNode(int index) {
    LinkedNode<Book> curNode = front;

    for (int i = 0; i < index; i++) {
      if (curNode == null) {
        throw new IllegalArgumentException("Index is not in range");
      }

      curNode = curNode.getNext();
    }

    return curNode;
  }

  /**
   * returns the book at the provided index
   * 
   * @param index index of wanted book
   * @return book at index
   */
  public Book get(int index) {
    return getNode(index).getData();
  }

  /**
   * returns the first book
   * 
   * @return the first book
   */
  public Book getFirst() {
    return front == null ? null : front.getData();
  }

  /**
   * returns the last book
   * 
   * @return the last book
   */
  public Book getLast() {
    return back == null ? null : back.getData();
  }

  /**
   * clears all elements
   */
  public void clear() {
    this.front = null;
    this.back = null;
  }

  /**
   * adds a book to the end of the list
   * 
   * @param toAdd book to add
   */
  public void appendBook(Book toAdd) {
    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);
    if (back != null) {
      back.setNext(newNode);
    }
    back = newNode;
    if (front == null) {
      front = newNode;
    }
    size++;

  }

  /**
   * inserts a book into a sorted list
   * 
   * @param toAdd book to insert
   */
  public void insertBook(Book toAdd) {
    LinkedNode<Book> curNode = front;

    if (curNode == null) {
      appendBook(toAdd);
      return;
    }

    LinkedNode<Book> newNode = new LinkedNode<Book>(toAdd);

    if (front.getData().compareTo(toAdd, sortedBy) > 0) {
      newNode.setNext(front);
      front = newNode;
      return;
    }

    while (curNode.getNext() != null && curNode.getNext().getData().compareTo(toAdd, sortedBy) < 0) {
      curNode = curNode.getNext();
    }

    LinkedNode<Book> tempNode = curNode.getNext();
    curNode.setNext(newNode);
    newNode.setNext(tempNode);
  }

  /**
   * uses insersion sort to sort the rearange the bookshelf in accordance to the
   * provided attribute
   * 
   * @param b      bookshelf to sort
   * @param sortBy attribute to sort by
   */
  public static void sort(LinkedBookshelf b, Attribute sortBy) {

    if (b.front == null)
      return; // an empty list is sorted

    b.sortedBy = sortBy;

    LinkedNode<Book> firstUnsortedNode = b.front;
    LinkedNode<Book> smallestUnsortedNode = b.front;
    LinkedNode<Book> prev = null;
    LinkedNode<Book> curNode = b.front; // first sorted node (after first pass)

    for (int i = 0; i < b.size(); i++) {

      // System.out.println(b);

      firstUnsortedNode = b.getNode(i);
      if (firstUnsortedNode == null)
        break;
      smallestUnsortedNode = firstUnsortedNode;
      curNode = firstUnsortedNode;
      prev = i == 0 ? null : b.getNode(i - 1);

      // find smallest unsorted node | edge case - smallest is head
      while (curNode != null && curNode.getNext() != null) {
        if (curNode.getNext().getData().compareTo(smallestUnsortedNode.getData(), sortBy) < 0) {
          smallestUnsortedNode = curNode.getNext();
          prev = curNode;
        }
        curNode = curNode.getNext();
      }
      // System.out.println("smallest: " + smallestUnsortedNode);

      // smallest node is at the head - already in correct place
      if (prev == null) {
        continue;
      }
      // otherwise remove smallest node from unsorted protion of list
      prev.setNext(smallestUnsortedNode.getNext());
      // System.out.println(b);

      b.insertBook(smallestUnsortedNode.getData());
      // System.out.println(b);
      // System.out.println();

      // find location to insert node
      // curNode = b.front;
      // prev = null;
      // while(curNode != firstUnsortedNode){

      // // smallestUnsortedNode goes before curNode
      // if(curNode.getData().compareTo(smallestUnsortedNode.getData(), sortBy) > 0){
      // break;
      // }

      // prev = curNode;
      // curNode = curNode.getNext();
      // }

      // if(prev == null){ // insert into head
      // smallestUnsortedNode.setNext(b.front);
      // b.front = smallestUnsortedNode;
      // } else if(i == b.size()) {
      // smallestUnsortedNode.setNext(null);
      // prev.setNext(smallestUnsortedNode);
      // } else {
      // smallestUnsortedNode.setNext(curNode);
      // prev.setNext(smallestUnsortedNode);

      // }
    }
  }
}