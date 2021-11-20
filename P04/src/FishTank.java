import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

interface TaskListener {
  // draws this tank object to the display window
  public void draw();

  // called each time the mouse is Pressed
  public void mousePressed();

  // called each time the mouse is Released
  public void mouseReleased();

  // checks whether the mouse is over this Tank GUI
  // return true if the mouse is over this tank GUI object, false otherwise
  public boolean isMouseOver();
}

public class FishTank extends PApplet {

  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TaskListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers

  public static void main(String[] args) {
    PApplet.main("FishTank");
  }

  // sets the size of this PApplet to 800 width x 600 height
  @Override
  public void settings() {
    size(800, 600);
  }

  // Defines initial environment properties such as screen size and
  // loads the background image and fonts as the program starts.
  // It also initializes all data fields.
  // The above IS NOT a javadoc style method header!
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

    backgroundImage = super.loadImage("images" + File.separator + "background.png");
    this.objects = new ArrayList<TaskListener>();
    randGen = new Random();
  }

  // Continuously draws and updates the application display window
  @Override
  public void draw() {
    super.image(backgroundImage, super.width / 2, super.height / 2);

    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }

  }

  // Callback method called each time the user presses the mouse
  @Override
  public void mousePressed() {
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mousePressed();
    }
    // TODO traverse the objects list and call mousePressed method
    // of the first object being clicked in the list

  }

  // Callback method called each time the mouse is released
  @Override
  public void mouseReleased() {
    for (int i = 0; i < objects.size(); i++) {
      if(objects.get(i).isMouseOver()){
        objects.get(i).mousePressed();
        break;
      }
    }
  }

  // adds an instance of TankListener passed as input to the objects arraylist
  public void addObject(TaskListener object) {
    objects.add(object);
  }

  // Callback method called each time the user presses a key
  @Override
  public void keyPressed() {

    // To be implemented later in the next sections

  }

}
