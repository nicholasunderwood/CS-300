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

import processing.core.PImage;

public class TankObject implements TankListener {
  protected static FishTank tank; // PApplet object which represents
  // the display window
  protected PImage image; // image of this tank object
  private float x; // x-position of this tank in the display window
  private float y; // y-position of this tank in the display window
  private boolean isDragging; // indicates whether this tank object
  // is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  public TankObject(float x, float y, String fileImageName) {
    this.x = x;
    this.y = y;
    this.image = tank.loadImage(fileImageName);
    this.isDragging = false;
  }

  /**
   * sets a static reference to the tank instance
   * 
   * @param tank the reference to the tank instance
   * @deprecated should use a static public singleton published in FishTank also
   *             why is this 7 file project not using packages if yall are gonna
   *             have us do javadoc comments and 2 space tabs at least conform to
   *             these standards make strucutre projects to insdustry standards
   */
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
  }

  /**
   * Moves this object with dx and dy
   * 
   * @param dx move to the x-position of this fish
   * @param dy move to the y-position of this fish
   */
  public void move(int dx, int dy) {
    x += dx;
    y += dy;
  }
  public void move(double dx, double dy) {
    x += dx;
    y += dy;
  }

  /**
   * Returns the image of this object
   * 
   * @return the image of type PImage of the object
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Returns the x-position of this object in the display window
   * 
   * @return the X coordinate of the object position
   */
  public float getX() {
    return x;
  }

  /**
   * Returns the y-position of this object in the display window
   * 
   * @return the Y coordinate of the object position
   */
  public float getY() {
    return y;
  }

  /**
   * Returns the x-position of this object in the display window
   * 
   * @return the X coordinate of the object position
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Returns the y-position of this object in the display window
   * 
   * @return the Y coordinate of the object position
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Checks whether this object is being dragged
   * 
   * @return true if the object is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Starts dragging this object
   * 
   */
  public void startDragging() {
    oldMouseX = tank.mouseX;
    oldMouseY = tank.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this object
   * 
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draws this object to the display window.
   */
  @Override
  public void draw() {

    if (this.isDragging) {
      int dx = tank.mouseX - oldMouseX;
      int dy = tank.mouseY - oldMouseY;
      move(dx, dy);
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }

    TankObject.tank.image(this.image, this.x, this.y);
  }

  @Override
  /**
   * Checks whether the mouse is over this decoration object
   * 
   * @return true if the mouse if over the object otherwise false
   */
  public boolean isMouseOver() {
    // checks if the mouse is over this object
    return tank.mouseX >= x - image.width / 2 && tank.mouseX <= x + image.width / 2
        && tank.mouseY >= y - image.height / 2 && tank.mouseY <= y + image.height / 2;
  }

  /**
   * initiates click on object
   */
  @Override
  public void mousePressed() {
    this.startDragging();

  }

  /**
   * initiates release of object
   */
  @Override
  public void mouseReleased() {
    this.stopDragging();
  }
}
