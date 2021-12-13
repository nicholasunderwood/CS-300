import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class models an iterator through a collection of tiles
 */
public class TileListIterator implements Iterator<Tile> {

    private Node node;

    /**
     * Creates a new iterator to iterate through a list of tiles starting from its
     * head
     * 
     * @param head reference to the head of the linked list of tiles to traverse
     */
    public TileListIterator(Node head) {
        this.node = head;
    }

    /**
     * Returns the next tile in the iteration
     * 
     * @return the next tile in the iteration
     * @throws NoSuchElementException if the iteration has no more tiles
     */
    @Override
    public Tile next() {
        if (node == null)
            throw new NoSuchElementException("Element does not exist");
        
        Tile tile = node.getTile();
        node = node.getNext();
        return tile;
    }

    /**
     * Checks whether there are more tiles in the iteration
     * 
     * @return true if iteration has more tiles, false otherwise
     */
    @Override
    public boolean hasNext() {
        return node != null;
    }
}
