//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    BinaryBookshelfTester.java
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

import java.util.ArrayList;

/**
 * Tests methods in P10
 */
public class BinaryBookshelfTester {

    /**
     * tests TreeNode
     * 
     * @return true iff TreeNode passes all tests
     */
    public static boolean testTreeNode() {

        try {
            Book.resetGenerator();

            // senario 1
            TreeNode<String> node = new TreeNode<String>("parent");
            if (!(node.getData() instanceof String) || !node.getData().equals("parent")) {
                System.out.println("1.1");
                return false;
            }

            if (node.getLeft() != null || node.getRight() != null) {
                System.out.println("1.2");
                return false;
            }

            if (!node.toString().equals("parent")) {
                System.out.println("1.3");
                return false;
            }

            // senario 2
            TreeNode<String> node1 = new TreeNode<String>("lchild");
            TreeNode<String> node2 = new TreeNode<String>("rchild");

            node.setLeft(node1);

            if (node.getLeft() != node1 || node.getRight() != null) {
                System.out.println("1.4");
                return false;
            }

            node.setRight(node2);

            if (node.getLeft() != node1 || node.getRight() != node2) {
                System.out.println("1.5");
                return false;
            }

            node.setLeft(null);

            if (node.getLeft() != null || node.getRight() != node2) {
                System.out.println("1.6");
                return false;
            }

            node.setRight(null);

            if (node.getLeft() != null || node.getRight() != null) {
                System.out.println("1.7");
                return false;
            }

            // senario 3
            TreeNode<String> parent = new TreeNode<String>("parent", node1, node2);

            if (parent.getLeft() != node1 || parent.getRight() != node2) {
                System.out.println("1.8");
                return false;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * tests trivial TreeNode
     * 
     * @return true iff TreeNode passes all tests
     */
    public static boolean testEmptyTree() {
        try {
            Book.resetGenerator();

            // senerio 1
            try {
                BinaryBookshelf b = new BinaryBookshelf(new Attribute[0]);
                return false;
            } catch (IllegalArgumentException e) {

            }

            try {
                BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.ID,
                        Attribute.PAGECOUNT, Attribute.TITLE, Attribute.ID });
                return false;
            } catch (IllegalArgumentException e) {

            }

            try {
                BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.ID,
                        Attribute.PAGECOUNT, Attribute.ID });
                return false;
            } catch (IllegalArgumentException e) {

            }

            try {
                BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.ID, Attribute.AUTHOR,
                        Attribute.PAGECOUNT, Attribute.TITLE });
                return false;
            } catch (IllegalArgumentException e) {

            }

            // Senerio 2
            BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.ID,
                    Attribute.PAGECOUNT, Attribute.TITLE });

            if (!b.isEmpty()) {
                return false;
            }

            if (b.size() != 0) {
                return false;
            }
            if (!b.toString().equals("")) {
                return false;
            }
            if (b.getRoot() != null) {
                return false;
            }

            if (!b.getAttributeOrder().equals("1:AUTHOR 2:ID 3:PAGECOUNT 4:TITLE")) {
                return false;
            }

            if (b.contains(new Book("", 1))) {
                return false;
            }

            if (!b.getBooksByAuthor("").isEmpty()) {
                return false;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * tests insertBook
     * 
     * @return true iff insertBook passes all tests
     */
    public static boolean testInsertBook() {

        try {
            Book.resetGenerator();

            BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.TITLE,
                    Attribute.PAGECOUNT, Attribute.ID });

            if (!b.isEmpty()) {
                return false;
            }

            Book book1 = new Book("2001", 296, "Clarke", "Arthur C");
            Book book5 = new Book("The Hobbit", 400, "Tolkien", "JRR");
            Book book6 = new Book("The Fellowship of the Ring", 500, "Tolkien", "JRR");

            // Scenario 1
            b.insertBook(book6);

            if (b.isEmpty() || b.size() != 1) {
                return false;
            }

            if (b.getRoot().getData() != book6) {
                return false;
            }

            if (b.getRoot().getLeft() != null || b.getRoot().getRight() != null) {
                return false;
            }

            // Scenario 2
            b.insertBook(book1);

            if (b.isEmpty() || b.size() != 2) {
                return false;
            }
            if (b.getRoot().getLeft().getData() != book1) {
                return false;
            }
            if (b.getRoot().getLeft().getLeft() != null || b.getRoot().getRight() != null) {
                return false;
            }

            // Scenario 3
            b.insertBook(book5);
            if (b.isEmpty() || b.size() != 3) {
                return false;
            }
            if (b.getRoot().getRight().getData() != book5) {
                return false;
            }
            if (b.getRoot().getLeft().getLeft() != null || b.getRoot().getRight().getLeft() != null) {
                return false;
            }

            // Scenario 4
            try {
                b.insertBook(book6);
                return false;
            } catch (IllegalArgumentException e) {

            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * tests contains
     * 
     * @return true iff contains passes all tests
     */
    public static boolean testContains() {
        try {
            Book.resetGenerator();

            BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.TITLE,
                    Attribute.PAGECOUNT, Attribute.ID });

            Book[] books = new Book[] {
                    new Book("2001", 296, "Clarke", "Arthur C") // 0
                    , new Book("Good Omens", 288, "Gaiman", "Neil") // 1
                    , new Book("FEED", 608, "Grant", "Mira") // 2
                    , new Book("Snow Crash", 468, "Stephenson", "Neal") // 3
                    , new Book("The Hobbit", 400, "Tolkien", "JRR") // 4
                    , new Book("The Fellowship of the Ring", 500, "Tolkien", "JRR") // 5
            };

            // Scenario 1
            b.insertBook(books[3]);

            for (Book book : books) {
                if (b.contains(book) ^ book == books[3]) {
                    return false;
                }
            }

            // Scenario 2
            TreeNode<Book> root = b.getRoot();
            root.setLeft(new TreeNode<Book>(
                    books[1],
                    new TreeNode<Book>(books[0]),
                    new TreeNode<Book>(books[2])));
            root.setRight(new TreeNode<Book>(
                    books[5],
                    null,
                    new TreeNode<Book>(books[4])));

            for (Book book : books) {
                if (!b.contains(book)) {
                    return false;
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * tests getBooksByAuthor
     * 
     * @return true iff getBooksByAuthor passes all tests
     */
    public static boolean testGetBooksByAuthor() {
        try {
            Book.resetGenerator();

            BinaryBookshelf b = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.TITLE,
                    Attribute.PAGECOUNT, Attribute.ID });

            // Scenario 1
            Book[] books = new Book[] {
                    new Book("2001", 296, "Clarke", "Arthur C") // 0
                    , new Book("Good Omens", 288, "Gaiman", "Neil") // 1
                    , new Book("FEED", 608, "Grant", "Mira") // 2
                    , new Book("Snow Crash", 468, "Stephenson", "Neal") // 3
                    , new Book("The Hobbit", 400, "Tolkien", "JRR") // 4
                    , new Book("The Fellowship of the Ring", 500, "Tolkien", "JRR") // 5
            };

            b.insertBook(books[3]);
            ArrayList<Book> filtered = b.getBooksByAuthor(books[3].getAuthor());
            if (filtered.size() != 1 || filtered.get(0) != books[3]) {
                return false;
            }

            // Scenario 2
            TreeNode<Book> root = b.getRoot();
            root.setLeft(new TreeNode<Book>(
                    books[1],
                    new TreeNode<Book>(books[0]),
                    new TreeNode<Book>(books[2])));
            root.setRight(new TreeNode<Book>(
                    books[5],
                    null,
                    new TreeNode<Book>(books[4])));

            BinaryBookshelf b2 = new BinaryBookshelf(new Attribute[] { Attribute.AUTHOR, Attribute.TITLE,
                    Attribute.PAGECOUNT, Attribute.ID });

            for (int i = 0; i < books.length - 1; i++) {
                filtered = b.getBooksByAuthor(books[i].getAuthor());

                if (i != 4) {
                    if (filtered.size() != 1) {
                        return false;
                    }

                    if (filtered.size() > 0 && filtered.get(0) != books[i]) {
                        return false;
                    }

                } else if (filtered.size() != 2 && filtered.get(0) != books[i] && filtered.get(1) != books[i + 1]) {
                    return false;
                }

            }

            filtered = b.getBooksByAuthor("none");
            if (!filtered.isEmpty()) {
                return false;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * runs all tests
     * 
     * @return true iff all tests are true
     */
    public static boolean runAllTests() {
        boolean hasNotFailed = true;

        hasNotFailed &= testTreeNode();
        hasNotFailed &= testEmptyTree();
        hasNotFailed &= testInsertBook();
        hasNotFailed &= testContains();
        hasNotFailed &= testGetBooksByAuthor();

        return hasNotFailed;
    }

    public static void main(String[] args) {
        System.out.println(runAllTests());
    }
}