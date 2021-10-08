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

import java.io.File;
import java.util.Random;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Runs PApllet gui of a Fish Tank
 */
public class FishTank {
    private static PApplet processing; // PApplet object which represents the graphic interface
                                       // of the Fish Tank application
    private static PImage backgroundImage; // PImage object which represents the background image
    private static Fish[] fishes; // array storing the current fishes present in the tank
    private static Random randGen; // Generator of random numbers

    // circular indexed array of fish images names
    private static String[] images = new String[] { "orange.png", "blue.png", "yellow.png", "black.png" };
    // index of next fish image index in the circular array images
    private static int nextImageIndex;

    // array storing the decoration objects present in the tank
    private static Decoration[] decorations;

    private static int fishSpeed = 5;

    /**
     * Defines initial environment properties such as screen size and to load
     * background images and fonts as the program starts. It also initializes all
     * data fields.
     * 
     * @param processingObj a PApplet object that represents the display window of
     *                      the Fish Tank application
     */
    public static void setup(PApplet processingObj) {
        processing = processingObj;
        backgroundImage = processing.loadImage("images" + File.separator + "background.png");
        fishes = new Fish[8];
        randGen = new Random();

        // add decorations
        decorations = new Decoration[4];
        decorations[0] = new Decoration(processing, 430, 60, "images" + File.separator + "flower.png");
        decorations[1] = new Decoration(processing, 580, 470, "images" + File.separator + "log.png");
        decorations[2] = new Decoration(processing, 65, 520, "images" + File.separator + "shell.png");
        decorations[3] = new Decoration(processing, 280, 535, "images" + File.separator + "ship.png");
    }

    /**
     * Continuously draws and updates the application display window
     */
    public static void draw() {
        // clear the display window by drawing the background image
        processing.image(backgroundImage, processing.width / 2, processing.height / 2);

        // traverse the fishes array and draw each of the fish present in the tank
        for (int i = 0; i < decorations.length; i++) {
            if (decorations[i] == null)
                continue;
            decorations[i].draw();
        }

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
        // traverse the fishes array and start dragging a fish if the mouse is over it
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] != null && fishes[i].isMouseOver()) {
                fishes[i].startDragging();
                return;
            }
        }

        // traverse the decoratons array and start dragging a decoration if the mouse is
        // over it
        for (int i = 0; i < decorations.length; i++) {
            if (decorations[i] != null && decorations[i].isMouseOver()) {
                decorations[i].startDragging();
                return;
            }
        }
    }

    /**
     * Callback method called each time the mouse is released
     */
    public static void mouseReleased() {
        // traverse the fishes array and stop dragging any fish
        for (int i = 0; i < fishes.length; i++) {
            if (fishes[i] == null) continue;
            fishes[i].stopDragging();
        }

        for (int i = 0; i < decorations.length; i++) {
            if(decorations[i] == null) continue;
            decorations[i].stopDragging();
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

                fishes[i] = new Fish(processing, (float) randGen.nextInt(processing.width),
                        (float) randGen.nextInt(processing.height), FishTank.fishSpeed,
                        "images" + File.separator + images[nextImageIndex]);
                nextImageIndex = (nextImageIndex + 1) % images.length;
                break;
            }
        } else if (processing.key == 'r' || processing.key == 'R') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null || !fishes[i].isMouseOver())
                    continue;

                fishes[i] = null;
                break;
            }
        } else if (processing.key == 's' || processing.key == 'S') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null)
                    continue;

                fishes[i].startSwimming();
            }
        } else if (processing.key == 'x' || processing.key == 'X') {
            for (int i = 0; i < fishes.length; i++) {
                if (fishes[i] == null)
                    continue;

                fishes[i].stopSwimming();
            }
        }
    }

    /**
     * This main method starts the application
     * 
     * @param args input arguments if any
     */
    public static void main(String[] args) {
        // starts the application
        Utility.startApplication();
    }

}
