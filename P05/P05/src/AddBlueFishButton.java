//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    An PApplet GUI of a fish tank 
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
 * Button that adds a blue fish
 */
public class AddBlueFishButton extends Button {
    
    /**
     * Constructs a new AddBlueFishButton
     * @param x x position of button
     * @param y y position of button
     */
    public AddBlueFishButton(float x, float y){
        super("Add blue", x, y);
    }

    /**
     * Adds a blue fish to the tank on click
     */
    @Override
    public void mousePressed() {
        tank.addObject(new BlueFish());
    }

}
