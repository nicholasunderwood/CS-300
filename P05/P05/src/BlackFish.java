import java.io.File;

import javax.swing.text.StyledEditorKit;

//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Contains the defintion of the blackfish object 
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
 * The Blackfish object
 */
public class BlackFish extends Fish {

  private TankObject source;
  private TankObject destination;

  /**
   * constructs a new BlackFish
   * 
   * @param source      the origin of the BlackFish
   * @param destination the end destination of the BlackFish
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images" + File.separator + "black.png");
    this.source = source;
    this.destination = destination;
  }

  /**
   * makes one speed move towards destination
   */
  public void moveTowardsDestination() {
    float dx = destination.getX() - this.getX();
    float dy = destination.getY() - this.getY();

    int mag = (int) Math.sqrt(dx * dx + dy * dy);

    float newX = getX() + (dx * getSpeed() / mag);
    float newY = getY() + (dy * getSpeed() / mag);


    // move(dxNorm, dyNorm); // int rounding creates bugs
    setX(newX);
    setY(newY);
  }

  /**
   * checks if the fish has reached another TankObject
   * 
   * @param other reference TankObject
   * @return true if this black fish is over another tank object, and false
   *         otherwise
   */
  public boolean isOver(TankObject other) {
    // get bounds of target
    int x1 = (int) other.getX() - other.image.width / 2;
    int x2 = (int) other.getX() + other.image.width / 2;
    int y1 = (int) other.getY() - other.image.height / 2;
    int y2 = (int) other.getY() + other.image.height / 2;

    // get bounds of this
    int x3 = (int) getX() - image.width / 2;
    int x4 = (int) getX() + image.width / 2;
    int y3 = (int) getY() - image.height / 2;
    int y4 = (int) getY() + image.height / 2;

    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
    // return (x1 >= x4) || (x3 >= x2) || (y1 >= y4) || (y3 >= y2);
  }

  /**
   * moves the fish toward its destination until it reaches it
   */
  @Override
  public void swim() {
    System.out.println(isOver(destination));
    if (isOver(destination)) {
      TankObject temp = source;
      source = destination;
      destination = temp;
    }

    moveTowardsDestination();
  }
}
