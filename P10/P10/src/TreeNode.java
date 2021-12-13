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

/**
 * A generic binary tree node
 */
public class TreeNode<T> {

    private T data;
    private TreeNode<T> leftNode;
    private TreeNode<T> rightNode;

    /**
     * Constructs a node with the given data and no children
     * 
     * @param data the data to be contained in this node
     */
    public TreeNode(T data) {
        this(data, null, null);
    }

    /**
     * Constructs a node with the given data and children
     * 
     * @param data      the data to be contained in this node
     * @param leftNode  the left child of this node, may be null
     * @param rightNode the right child of this node, may be null
     */
    public TreeNode(T data, TreeNode<T> leftNode, TreeNode<T> rightNode) {
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    /**
     * Accessor for the data contained in the node
     * 
     * @return the data contained in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Accessor for the left child of the node
     * 
     * @return a reference to the left child of this node
     */
    public TreeNode<T> getLeft() {
        return leftNode;
    }

    /**
     * Accessor for the right child of the node
     * 
     * @return a reference to the right child of this node
     */
    public TreeNode<T> getRight() {
        return rightNode;
    }

    /**
     * Change the left child reference of this node; may be null
     * 
     * @param newLeft the new left child reference
     */
    public void setLeft(TreeNode<T> newLeft) {
        this.leftNode = newLeft;
    }

    /**
     * Change the right child reference of this node; may be null
     * 
     * @param newRight the new right child reference
     */
    public void setRight(TreeNode<T> newRight) {
        this.rightNode = newRight;
    }

    /**
     * Returns a string representation of this node's data
     * 
     * @return a string representation of this node's data
     */
    @Override
    public String toString() {
        return data.toString();
    }

}
