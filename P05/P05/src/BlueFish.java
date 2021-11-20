//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Contains a representation of a blue fish object 
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

import java.io.File;

/**
 * A representation of a blue fish object
 */
public class BlueFish extends Fish {

  /**
   * initates a new blue fish
   * 
   * @param speed how fast the fish swims
   */
  public BlueFish() {
    super(2, "images" + File.separator + "blue.png");
  }

  /**
   * moves the fish backward around the screen
   */
  @Override
  public void swim() {
    System.out.println(getX() + " " + (getX() - getSpeed()) % tank.width);
    setX((getX() - getSpeed() + tank.width) % tank.width);
  }
}
