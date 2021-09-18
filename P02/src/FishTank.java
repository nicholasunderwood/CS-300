//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Class contains static methods which create an PApplet gui of a fish tank 
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
import java.io.File;
import java.rmi.ConnectIOException;

import processing.core.PApplet;
import processing.core.PImage;

public class FishTank {

    private static PApplet processing; // PApplet object that represents the graphic
    private static PImage backgroundImage; // PImage object that represents the background image
    private static Fish[] fishes; // perfect size array storing the different fish present
    private static Random randGen; // Generator of random numbers

    /**
     * Checks if the mouse is over a specific Fish whose reference is provided as
     * input parameter
     *
     * @param Fish reference to a specific fish
     * @return true if the mouse is over the specific Fish object (i.e. over the
     *         image of the Fish), false otherwise
     */
    public static boolean isMouseOver(Fish fish) {
        PImage fishImage = fish.getImage();
        float fishWidth = fishImage.width, fishHeight = fishImage.height;
        float fishX = fish.getPositionX(), fishY = fish.getPositionY();
        float mouseX = (float) processing.mouseX, mouseY = (float) processing.mouseY;

        // determine if mouse pos is in fish bounding box
        return (mouseX < fishX + fishWidth / 2 && mouseX > fishX - fishWidth / 2 && mouseY < fishY + fishHeight / 2
                && mouseY > fishY - fishHeight / 2);
    }

    /**
     * creates a new instance of the fish object at a random position
     * 
     * @return an instance of Fish
     */
    public static Fish createRandomFish() {
        float x = (float) randGen.nextInt(processing.width);
        float y = (float) randGen.nextInt(processing.height);
        return new Fish(processing, x, y);
    }

    /**
     * Defines the initial environment properties of this application
     * 
     * @param processingObj a reference to the graphic display window of this
     *                      application
     */
    public static void setup(PApplet processingObj) {
        System.out.println("Setup");
        processing = processingObj;

        // Set background image
        backgroundImage = processing.loadImage("images/background.png");

        // create a random obj
        randGen = new Random();

        // instansiate fishes array
        fishes = new Fish[8];
    }

    /**
     * Draws and updates the application display window. This callback method called
     * in an infinite loop.
     */
    public static void draw() {

        // draw background
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        // draw all fish
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null)
                continue;

            fishes[i].draw();
        }
    }

    /**
     * Callback method called each time the user presses the mouse
     */
    public static void mousePressed() {

        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null)
                continue;

            if (isMouseOver(fishes[i])) {
                fishes[i].setDragging(true);
                break;
            }
        }
    }

    /**
     * Callback method called each time the mouse is released
     */
    public static void mouseReleased() {
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null)
                continue;

            fishes[i].setDragging(false);
        }
    }

    /**
     * Callback method called each time the user presses a key
     */
    public static void keyPressed() {

        // add fish on key press
        if (processing.key == 'f' || processing.key == 'F') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] != null)
                    continue;

                fishes[i] = createRandomFish();
                break;
            }
        } else if (processing.key == 'r' || processing.key == 'R') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null || !isMouseOver(fishes[i]))
                    continue;

                fishes[i] = null;
                break;
            }
        }
    }

    public static void main(String[] args) {
        Utility.startApplication();
    }
}
