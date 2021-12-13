//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    TreeNode.java
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
 * A binary search tree version of our sorted Bookshelf, where all Books are
 * sorted based on (in decreasing order of importance) the Attributes contained
 * in the sortList. Books must be compared first based on their authors, and
 * then on the other Attributes in the order they appear in the sortList.
 */
public class BinaryBookshelf {
    private TreeNode<Book> root;
    private int size;
    private Attribute[] sortList;

    /**
     * One-argument constructor, initializes an empty BinaryBookshelf
     * 
     * @param sortList an ordered array of Attributes, must begin with AUTHOR and
     *                 contain exactly one copy of each Attribute in the enum
     * @throws IllegalArgumentException if the sortList argument is not a
     *                                  four-element array of unique attributes,
     *                                  beginning with Attribute.AUTHOR
     */
    BinaryBookshelf(Attribute[] sortList) {
        if (sortList.length != 4) {
            throw new IllegalArgumentException("sortList does not have four elements");
        }

        for (Attribute att : Attribute.values()) {
            boolean isContained = false;

            for (Attribute testAtt : sortList) {
                if (testAtt == att) {
                    isContained = true;
                    break;
                }
            }

            if (!isContained) {
                throw new IllegalArgumentException("sortList does not contain all Attributes");
            }
        }

        if (sortList[0] != Attribute.AUTHOR) {
            throw new IllegalArgumentException("First element of sortlist is not author");
        }

        this.sortList = sortList;
        this.size = 0;
    }

    /**
     * Resets the BST to be empty
     */
    public void clear() {
        size = 0;
        this.root = null;
    }

    /**
     * Searches for the input book in the bookshelf. This must be implemented
     * recursively; do not call toString() in this method.
     * Time Complexity where the the problems size is the number of nodes
     * Worst Case: O(logN) if the target is a leaf node
     * Best Case: O(1) if the target is the root node
     * 
     * @param book the book to search for
     * @return true if the book is present in the shelf, false otherwise
     */
    public boolean contains(Book book) {
        return containsHelper(book, root);
    }

    /**
     * Recursive helper method; searches for the input book in the subtree rooted at
     * current
     * 
     * @param book    the query book to search for
     * @param current the root of the current subtree
     * @return true if the book is contained in this subtree, false otherwise
     */
    protected boolean containsHelper(Book book, TreeNode<Book> current) {
        if (current == null) {
            return false;
        }
        if (book.equals(current.getData())) {
            return true;
        }

        int compareVal = compareToHelper(book, current.getData());
        if (compareVal < 0) {
            return containsHelper(book, current.getLeft());
        } else {
            return containsHelper(book, current.getRight());
        }

    }

    /**
     * Provides a String-formatted list of the attributes in the order in which they
     * are used
     * 
     * @return a String-formatted list of the sorting attributes
     */
    public String getAttributeOrder() {
        String str = "";

        for (int i = 0; i < sortList.length; i++) {
            str += (i + 1) + ":" + sortList[i].toString() + " ";
        }

        return str.strip();
    }

    /**
     * Returns a list of books in the bookshelf written by the given author
     * 
     * @param authorName the author name to filter on
     * @return a list of books by the author
     */
    public ArrayList<Book> getBooksByAuthor(String authorName) {
        return getBooksByAuthorHelper(authorName, root);
    }

    /**
     * Recursive helper method; returns a list of books in the subtree rooted at
     * current which were written by the given author
     * 
     * @param authorName the author name to filter on
     * @param current    the root of the current subtree
     * @return a list of books by the author in the current subtree
     */
    protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
        ArrayList<Book> books = new ArrayList<Book>();

        if (current == null) {
            return books;
        }

        // boolean hasAuthor = current.getData().getAuthor().equals(authorName);
        int compareVal = authorName.compareTo(current.getData().getAuthor());

        if (compareVal == 0) {
            books.add(current.getData());
            books.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
            books.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
        } else if(compareVal < 0){
            books.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
        } else if (compareVal > 0){
            books.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
        }

        return books;
    }

    /**
     * Returns a shallow copy of the root node so that test tree structures may be
     * constructed manually rather than by using the insertBook() method
     * 
     * @return a reference to the current root node
     */
    protected TreeNode<Book> getRoot() {
        return this.root;
    }

    /**
     * Adds a new Book to the BST in sorted order, relative to this BST's sortList
     * attributes Note: you may wish to write helper methods for comparing Books
     * according to the sortList, as well as for inserting Books in a recursive
     * manner.
     * 
     * @param book the Book object to be added to the BST
     * @throws IllegalArgumentException if the book already exists in the BST
     */
    public void insertBook(Book book) {
        if (root == null) {
            root = new TreeNode<Book>(book);
        } else {
            insertBookHelper(book, root);
        }

        size++;
    }

    /**
     * helper method to compare two Book objects according to the sortList of
     * attributes. Uses both equals() and compareTo() from the Book class
     * 
     * @param one the first book
     * @param two the second book
     * @return a negative value if one < two, a positive value if one > two, and 0
     *         if they are equal
     */
    protected int compareToHelper(Book one, Book two) {
        int compareVal;
        for (int attrIndex = 0; attrIndex < sortList.length; attrIndex++) {
            compareVal = one.compareTo(two, sortList[attrIndex]);
            if (compareVal != 0) {
                return compareVal;
            }
        }

        return 0;
    }

    /**
     * Recursive helper method; adds the given Book to the subtree rooted at
     * current.
     * 
     * @param book    the Book object to be added to the BST
     * @param current the root of the current subtree
     * @throws IllegalArgumentException if the book already exists in the BST
     */
    protected void insertBookHelper(Book book, TreeNode<Book> current) {

        if (book.equals(current.getData())) {
            throw new IllegalArgumentException("Book already exists in BST");
        }

        int compareVal = compareToHelper(book, current.getData());
        boolean insertIntoLeft = compareVal < 0;
        TreeNode<Book> nextNode = insertIntoLeft ? current.getLeft() : current.getRight();

        if (nextNode == null) {
            nextNode = new TreeNode<Book>(book);
            if (insertIntoLeft) {
                current.setLeft(nextNode);
            } else {
                current.setRight(nextNode);
            }
        } else {
            insertBookHelper(book, nextNode);
        }
    }

    /**
     * Determine whether the BST is empty
     * Time Complexity where the the problems size is the number of nodes
     * Worst Case: O(1)
     * Best Case: O(1) 
     * 
     * @return true if the BST is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the number of nodes currently in the BST
     * Time Complexity where the the problems size is the number of nodes
     * Worst Case: O(1)
     * Best Case: O(1) 
     * 
     * @return the number of nodes currently in the BST
     */
    public int size() {
        return this.size;
    }

    /**
     * Creates and returns an in-order traversal of the BST, with each Book on a
     * separate line
     * Time Complexity where the the problems size is the number of nodes
     * Worst Case: O(N)
     * Best Case: O(N) 
     * 
     * @returns an in-order traversal of the BST, with each Book on a separate line
     */
    @Override
    public String toString() {
        return toStringHelper(this.root, 1);
    }

    /**
     * Recursive helper method; creates and returns the String representation of the
     * subtree rooted at the current node
     * 
     * @param current the root of the current subtree
     * @return the string representation of this subtree
     */
    protected String toStringHelper(TreeNode<Book> current, int depth) {

        if (current == null) {
            return "";
        }

        String str = "|".repeat(depth) + current.toString();

        str = toStringHelper(current.getLeft(), depth+1) + "\n" + str;
        str = str + "\n" + toStringHelper(current.getRight(), depth+1);

        return str;
    }

    /**
     * A helper method to visualize the tree
     */
    protected void printBinaryTree() {
        printBinaryTree(root, 0);
    }

    /**
     * A helper method to visualize the tree
     * 
     * @param current root of current node
     * @param depth   depth of current node
     */
    protected void printBinaryTree(TreeNode<Book> current, int depth) {
        System.out.println("|".repeat(depth) + current.toString());

        boolean isLeaf = current.getLeft() == null && current.getRight() == null;

        if (current.getLeft() != null) {
            printBinaryTree(current.getLeft(), depth + 1);
        } else if (isLeaf) {
            System.out.println("|".repeat(depth + 1) + "empty");
        }

        if (current.getRight() != null) {
            printBinaryTree(current.getRight(), depth + 1);
        } else if (isLeaf) {
            System.out.println("|".repeat(depth + 1) + "empty");
        }
    }
}