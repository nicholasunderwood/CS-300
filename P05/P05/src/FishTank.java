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

import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The main class of fish tank
 */
public class FishTank extends PApplet {

  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers

  private TankObject flower;
  private TankObject log;
  private TankObject shell;
  private TankObject ship;

  public static void main(String[] args) {
    PApplet.main("FishTank");
  }

  /**
   * sets the size of this PApplet to 800 width x 600 height
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties such as screen size and loads the
   * background image and fonts as the program starts. *
   */
  @Override
  public void setup() {

    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
    // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // passes static reference to FishTank
    TankObject.setProcessing(this);
    Button.setProcessing(this);
    // loads the background image
    backgroundImage = super.loadImage("images" + File.separator + "background.png");
    // instanitates an empty arraylist
    objects = new ArrayList<TankListener>();
    // instanitates a random number genderator
    randGen = new Random();

    //instantiate decorations
    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");

    // adds dectorations to objects
    objects.add(flower);
    objects.add(log);
    objects.add(shell);
    objects.add(ship);
    
    // adds black fish to objects
    objects.add(new BlackFish(log, flower));
    objects.add(new BlackFish(shell, flower));
    
    // add buttons to objects
    objects.add(new AddBlueFishButton(43, 16));
    objects.add(new AddOrangeFishButton(129, 16));
    objects.add(new AddYellowFishButton(215, 16));
    objects.add(new ClearTankButton(301, 16));
  }

  /**
   * Continuously draws and updates the application display window
   */
  @Override
  public void draw() {
    // draws the image to the center of the screen
    super.image(backgroundImage, super.width / 2, super.height / 2);

    // draws all objects stored in the objects array list
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }


  }

  /**
   * Controls the mouse down componet of the drag behavoir.
   */
  @Override
  public void mousePressed() {

    // traverse the objects list and calls mousePressed method of the first object
    // being clicked in the list
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      }
    }
  }

  /**
   * Controls the mouse pressed componet of the drag behavoir
   */
  @Override
  public void mouseReleased() {
    // calls mouse released on all objects
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  /**
   * adds a instance of tanklistener to objects
   * 
   * @param object TankListener object to be added to the tank
   */
  public void addObject(TankListener object) {
    if(object == null) return;
    objects.add(object);
  }

  /**
   * keyboard control for tank listener
   */
  @Override
  public void keyPressed() {
    if (key == 'o' || key == 'O') {
      addObject(new Fish());
    } else if (key == 'y' || key == 'Y') {
      addObject(new Fish(2, "images" + File.separator + "yellow.png"));
    } else if (key == 'b' || key == 'B') {
      addObject(new BlueFish());
    } else if (key == 'r' || key == 'R') {
      for (TankListener object : objects) {
        if (object instanceof Fish && object.isMouseOver()) {
          objects.remove(object);
          break;
        }
      }
    } else if (key == 's' || key == 'S') {
      Fish fish;
      for (TankListener object : objects) {
        if (object instanceof Fish) {
          fish = (Fish) object;
          fish.startSwimming();
        }
      }
    } else if (key == 'x' || key == 'X') {
      Fish fish;
      for (TankListener object : objects) {
        if (object instanceof Fish) {
          fish = (Fish) object;
          fish.stopSwimming();
        }
      }
    } else if (key == 'c' || key == 'C') {
      clear();
    }

  }

  /**
   * removes all fish
   */
  public void clear() {
    for (int i = objects.size()-1; i >= 0; i--) {
      if (objects.get(i) != null && objects.get(i) instanceof Fish) {
        objects.remove(i);
      }
    }
  }
}
