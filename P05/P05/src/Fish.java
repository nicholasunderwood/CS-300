//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    A representation of a fish object 
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
 * The Fish Class
 *
 */
public class Fish extends TankObject {

  private boolean isSwimming; // indicates whether this fish is swimming or not
  private int speed; // indicates whether this fish is swimming or not

  /**
   * Creates a new fish object positioned at the center of the display window
   * 
   * @param processing PApplet object that represents the display window
   */
  public Fish() {
    this(5, "images" + File.separator + "orange.png");
  }

  /**
   * Creates a new fish object positioned at a specific position of the display
   * window
   * 
   * @param x                 x-coordinate of the image of this fish in the
   *                          display window
   * @param y                 y-coordinate of the image of this fish in the
   *                          display window
   * @param speed             the swimming speed of this fish
   * @param fishImageFileName file name of the image of this fish
   * @throws IllegalArgumentException If speed is <= 0
   */
  public Fish(int speed, String fishImageFileName) {
    super((float) tank.randGen.nextInt(tank.width), (float) tank.randGen.nextInt(tank.height), fishImageFileName);

    // Set fish draw parameters
    this.image = tank.loadImage(fishImageFileName);
    // sets the position of this fish
    if(speed <= 0) throw new IllegalArgumentException("Warning: speed cannont be negitive");
    this.speed = speed;
  }

  /**
   * Draws this fish to the display window.
   */
  @Override
  public void draw() {

    // if the fish is swimming, call its swim() method
    if (isSwimming) {
      swim();
    }
    // draw the fish at its current position
    super.draw();
  }

  /**
   * getter method of the speed of the fish
   * @return the speed of the fish
   */
  public int getSpeed(){
    return speed;
  }

  /**
   * Starts swimming this fish
   * 
   */
  public void startSwimming() {
    this.stopDragging();
    this.isSwimming = true;
  }

  /**
   * Stops swimming this fish
   * 
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }

  /**
   * Moves horizontally the fish one speed step from left to right.
   */
  public void swim() {
    setX((getX() + speed) % tank.width);
  }

}
