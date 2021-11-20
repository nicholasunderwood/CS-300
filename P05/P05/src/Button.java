
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Button 
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
 * A clickable button
 */
public class Button implements TankListener {

  private static final int WIDTH = 85; // Width of this Button
  private static final int HEIGHT = 32; // Height of this Button
  protected static FishTank tank; // PApplet object where this button will be displayed
  private float x; // x-position of this button in the display window
  private float y; // y-position of this button in the display window
  protected String label; // text/label which represents this button

  /**
   * Constructs a new button
   * 
   * @param label
   * @param x
   * @param y
   */
  public Button(String label, float x, float y) {
    this.x = x;
    this.y = y;
    this.label = label;
  }

  /**
   * sets a static reference to the tank instance
   * 
   * @param tank the reference to the tank instance
   */
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  /**
   * draws the button to the screen
   */
  @Override
  public void draw() {
    tank.fill(isMouseOver() ? 200 : 100);
    tank.rect(x - WIDTH / 2, y + HEIGHT / 2, x + WIDTH / 2, y - HEIGHT / 2);
    tank.fill(255);
    tank.text(label, x, y);
  }

  /**
   * Overrides TankListener mousePressed
   * 
   * @deprecated should be abstract
   */
  @Override
  public void mousePressed() {
    System.out.println(label + "was pressed");
  }

  /**
   * Overrides TankListener mouseReleased
   * 
   * @deprecated should be abstract
   */
  @Override
  public void mouseReleased() {
  }

  /**
   * checks if mouse if over the button
   */
  @Override
  public boolean isMouseOver() {
    return tank.mouseX >= x - WIDTH / 2 && tank.mouseX <= x + WIDTH / 2 && tank.mouseY >= y - HEIGHT / 2
        && tank.mouseY <= y + HEIGHT / 2;
  }
}
