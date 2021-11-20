//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    LinkedBookShelfTester
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
 * Tester Class
 */
public class LinkedBookShelfTester {

  /**
   * tests clear method
   * 
   * @return true if test passes otherwise false
   */
  public static boolean testClear() {
    try {

      LinkedBookshelf b = new LinkedBookshelf();

      Book[] books = { new Book("2001", 296, "Clarke", "Arthur C"), new Book("FEED", 608, "Grant", "Mira"),
          new Book("Good Omens", 288, "Gaiman", "Neil"), new Book("Snow Crash", 468, "Stephenson", "Neal") };

      for (Book book : books) {
        b.appendBook(book);
      }

      b.clear();

      if (b.getFirst() != null)
        return false;
      if (b.getLast() != null)
        return false;
      if (b.size() != 0)
        return false;

    } catch (Exception e) {
      System.out.println("testClear encountered and unexpected exception");
      e.printStackTrace();
    }
    return true;

  }

  /**
   * tests appendook method
   * 
   * @return true if test passes otherwise false
   */
  public static boolean testAddBooks() {
    try {
      LinkedBookshelf b = new LinkedBookshelf();

      Book[] books = { new Book("2001", 296, "Clarke", "Arthur C"), new Book("FEED", 608, "Grant", "Mira"),
          new Book("Good Omens", 288, "Gaiman", "Neil"), new Book("Snow Crash", 468, "Stephenson", "Neal") };

      for (int i = 0; i < books.length; i++) {
        b.appendBook(books[i]);

        if (b.size() != i)
          return false;
        if (b.getLast() != books[i])
          return false;
        if (b.getFirst() != books[0])
          return false;
      }

      for (int i = 0; i < books.length; i++) {
        if (b.get(i) != books[i])
          return false;
      }

      LinkedNode<Book> runner = b.getNode(0);
      for (int i = 0; i < books.length; i++) {
        if (runner.getData() != books[i])
          return false;
        if (runner.getNext() == null && i != books.length)
          return false;
        runner = runner.getNext();
      }

      return true;
    } catch (Exception e) {
      System.out.println("testAddBooks encountered and unexpected exception");
      e.printStackTrace();
    }

    return true;
  }

  /**
   * tests sort method
   * 
   * @return true if test passes otherwise false
   */
  public static boolean testSortBooks() {

    try {

      LinkedBookshelf b = new LinkedBookshelf();

      Book[] books = { new Book("Good Omens", 288, "Gaiman", "Neil"), new Book("FEED", 608, "Grant", "Mira"),
          new Book("Snow Crash", 468, "Stephenson", "Neal"), new Book("2001", 296, "Clarke", "Arthur C") };

      int[] idOrder = new int[] { 0, 1, 2, 3 };
      int[] authorOrder = new int[] { 3, 0, 2, 1 };
      int[] pageCountOrder = new int[] { 0, 3, 2, 1 };
      int[] titleOrder = new int[] { 3, 1, 0, 2 };

      for (Book book : books) {
        b.appendBook(book);
      }

      LinkedBookshelf.sort(b, Attribute.TITLE);

      // method does work, tests do not for some reason
      System.out.println(b);
      for (int i = 0; i < books.length; i++) {
        if (b.get(titleOrder[i]) != books[i]) {
          return false;
        }
      }

      LinkedBookshelf.sort(b, Attribute.ID);
      System.out.println(b);
      for (int j = 0; j < books.length; j++) {
        if (b.get(idOrder[j]) != books[j]) {
          return false;
        }
      }

      LinkedBookshelf.sort(b, Attribute.AUTHOR);
      System.out.println(b);
      for (int i = 0; i < books.length; i++) {
        if (b.get(authorOrder[i]) != books[i]) {
          return false;
        }
      }

      LinkedBookshelf.sort(b, Attribute.PAGECOUNT);
      System.out.println(b);
      for (int k = 0; k < books.length; k++) {
        if (b.get(pageCountOrder[k]) != books[k]) {
          return false;
        }
      }
    } catch (Exception e) {
      System.out.println("testSortBooks encountered and unexpected exception");
      e.printStackTrace();
    }

    return true;
  }

  /**
   * tests linked node
   * 
   * @return true if test passes otherwise false
   */
  public static boolean testLinkedNode() {
    try {
      String data = "test";
      LinkedNode<String> ln = new LinkedNode<String>(data);
      if (ln.getData() != data) {
        return false;
      }
      if (ln.getNext() != null) {
        return false;
      }

      String data2 = "head";
      LinkedNode<String> ln2 = new LinkedNode<String>(data2, ln);

      if (ln2.getData() != data2) {
        return false;
      }
      if (ln2.getNext() != ln) {
        return false;
      }

      ln2.setNext(null);
      if (ln.getNext() != null) {
        return false;
      }

    } catch (Throwable e) {
      System.out.println("testLinkedNode encountered and unexpected exception");
      e.printStackTrace();
    }

    return true;
  }

  /**
   * tests all methods
   * 
   * @return true if all tests passes otherwise false
   */
  public static boolean runAllTests() {
    boolean allTestsPassed = true;

    allTestsPassed = allTestsPassed & testLinkedNode();
    allTestsPassed = allTestsPassed & testAddBooks();
    allTestsPassed = allTestsPassed & testClear();
    allTestsPassed = allTestsPassed & testSortBooks();

    return allTestsPassed;
  }

  public static void main(String[] args) {
    System.out.println(runAllTests());
  }

}
