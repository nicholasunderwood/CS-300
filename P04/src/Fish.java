import processing.core.PApplet;
import processing.core.PImage;


/**
 * Represents a Fish to be used in Main class FishTank
 */
public class Fish {

    static private PApplet processing;
    static private int oldMouseX, oldMouseY;
    private PImage image;
    private float x, y;
    private int speed;
    private boolean isDragging, isSwimming;


    /**
     * constructs a new fish
     * @param processing static reference to canvas
     * @param x x position of the fish
     * @param y y position of the fish
     * @param speed speed of the fish
     * @param fishImageFileName full file location of png image
     */
    public Fish(PApplet processing, float x, float y, int speed, String fishImageFileName) {
        Fish.processing = processing;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = processing.loadImage(fishImageFileName);

    }

    /**
     * @return the image of type PImage of this fish
     */
    public PImage getImage() {
        return image;
    }

    /**
     * @return the x-position of this fish in the display window
     */
    public float getPositionX() {
        return x;
    }

    /**
     * @return the y-position of this fish in the display window
     */
    public float getPositionY() {
        return y;
    }

    /**
     * moves the fish
     * @param dx delta x
     * @param dy delta y
     */
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * @return whether this fish is being dragged
     */
    public boolean isDragging() {
        return isDragging;
        // a getter for the isDragging instance field
    }

    /**
     * starts dragging the fish
     */
    public void startDragging() {
        Fish.oldMouseX = processing.mouseX;
        Fish.oldMouseY = processing.mouseY;
        this.isDragging = true;
    }

    /**
     * stops dragging the fish
     */
    public void stopDragging() {
        this.isDragging = false;

    }

    /**
     * Checks if the mouse is over a given fish whose reference is provided as input
     * parameter
     * 
     * @return true if the mouse is over the given fish object (i.e. over the image
     *         of the fish), false otherwise
     */
    public boolean isMouseOver() {
        int fishWidth = this.getImage().width;
        int fishHeight = this.getImage().height;

        // checks if the mouse is over the provided fish
        return processing.mouseX >= this.getPositionX() - fishWidth / 2
                && processing.mouseX <= this.getPositionX() + fishWidth / 2
                && processing.mouseY >= this.getPositionY() - fishHeight / 2
                && processing.mouseY <= this.getPositionY() + fishHeight / 2;
    }

    /**
     * initiates swimming for the fish
     */
    public void startSwimming() {
        stopDragging();
        this.isSwimming = true;

    }

    /**
     * deactivates swimming for the fish
     */
    public void stopSwimming() {
        this.isSwimming = false;
    }

    /**
     * moves the fish around the tank
     */
    public void swim() {
        this.x = (this.x + speed) % processing.width;
    }

    /**
     * Draws this fish to the display window. This method sets also the position of
     * this fish to follow the moves of the mouse if it is being dragged
     */
    public void draw() {

        if (this.isDragging()) {

            this.move(processing.mouseX - Fish.oldMouseX, processing.mouseY - Fish.oldMouseY);
            Fish.oldMouseX = processing.mouseX;
            Fish.oldMouseY = processing.mouseY;
        } else if (this.isSwimming) {
            this.swim();
        }

        processing.image(this.image, x, y);
    }

}
