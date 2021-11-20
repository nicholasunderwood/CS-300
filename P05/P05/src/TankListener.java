//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    An interface containing classes used by fish tank objects 
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
 * Task Listener Interface
 */
public interface TankListener {
    /**
     * 
     */
    public void draw();
    // called each time the mouse is Pressed
    public void mousePressed();
    // called each time the mouse is Released
    public void mouseReleased();
    // checks whether the mouse is over this Tank GUI
    // return true if the mouse is over this tank GUI object, false otherwise
    public boolean isMouseOver();
}
