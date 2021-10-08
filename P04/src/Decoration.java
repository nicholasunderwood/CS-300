import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents a Decoration to be used in Main class FishTank
 */
public class Decoration {

    static private PApplet processing;
    static private int oldMouseX, oldMouseY;
    private PImage image;
    private float x, y;
    private boolean isDragging;

    /**
     * constructs a new decoration
     * 
     * @param processing              static reference to canvas
     * @param x                       x position of the decoration
     * @param y                       y position of the decoration
     * @param decorationImageFileName full file location of png image
     */
    public Decoration(PApplet processing, float x, float y, String decorationImageFileName) {
        Decoration.processing = processing;
        this.x = x;
        this.y = y;
        this.image = processing.loadImage(decorationImageFileName);

    }

    /**
     * getter for image parameter
     * 
     * @return the image of type PImage of this decoration
     */
    public PImage getImage() {
        return image;
    }

    /**
     * getter for x parameter
     * 
     * @return the x-position of this decoration in the display window
     */
    public float getPositionX() {
        return x;
    }

    /**
     * getter for y parameter
     * 
     * @return the y-position of this decoration in the display window
     */
    public float getPositionY() {
        return y;
    }

    /**
     * moves the decoration
     * 
     * @param dx delta x
     * @param dy delta y
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * getter for isDragging parameter
     * 
     * @return whether this decoration is being dragged
     */
    public boolean isDragging() {
        return isDragging;
        // a getter for the isDragging instance field
    }

    /**
     * Starts draggging this decoration
     */
    public void startDragging() {
        Decoration.oldMouseX = processing.mouseX;
        Decoration.oldMouseY = processing.mouseY;
        this.isDragging = true;
    }

    /**
     * Stops draggging this decoration
     */
    public void stopDragging() {
        this.isDragging = false;

    }

    /**
     * Checks if the mouse is over a given decoration whose reference is provided as
     * input parameter
     * 
     * @return true if the mouse is over the given decoration object (i.e. over the
     *         image of the decoration), false otherwise
     */
    public boolean isMouseOver() {
        int decorationWidth = this.getImage().width;
        int decorationHeight = this.getImage().height;

        // checks if the mouse is over the provided decoration
        return processing.mouseX >= this.getPositionX() - decorationWidth / 2
                && processing.mouseX <= this.getPositionX() + decorationWidth / 2
                && processing.mouseY >= this.getPositionY() - decorationHeight / 2
                && processing.mouseY <= this.getPositionY() + decorationHeight / 2;
    }

    /**
     * Draws this decoration to the display window. This method sets also the
     * position of this decoration to follow the moves of the mouse if it is being
     * dragged
     */
    public void draw() {

        if (this.isDragging()) {

            this.move(processing.mouseX - Decoration.oldMouseX, processing.mouseY - Decoration.oldMouseY);
            Decoration.oldMouseX = processing.mouseX;
            Decoration.oldMouseY = processing.mouseY;
        }

        processing.image(this.image, x, y);
    }

}
