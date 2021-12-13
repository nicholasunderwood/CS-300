// File header comes here

/**
 * This class models a Tile of a specific color
 *
 */
/**
 * @author mouna
 *
 */
public class Tile {
  private Color color; // color of this Tile

  /**
   * Creates a Tile with a specific color
   * @param black color to be assigned to this tile
   */
  public Tile(Color black) {
    this.color = black;
  }

  /**
   * Gets the color of this tile
   * @return the color of this tile
   */
  public Color getColor() {
    return color;
  }


  /**
   * Returns a string representation of this tile
   * @return the color of this tile as a string
   */
  @Override
  public String toString() {
    return color.name();
  }

  
  /**
   * Checks whether this tile equals to the other object provided as input
   * @return true if other is a Tile and has the same color as this tile
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof Tile && this.color == ((Tile) other).color;
  }
}
