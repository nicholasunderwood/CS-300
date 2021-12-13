import java.util.EmptyStackException;
import java.util.Iterator;

public class TileStack implements Iterable<Tile>, StackADT<Tile> {
    private Node top;
    private int size;

    /**
     * Check whether this stack is empty
     * 
     * @return true if this stack contains no elements, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the tile at the top of this stack
     * 
     * @return the tile at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    @Override
    public Tile peek() {
        if (isEmpty())
            throw new EmptyStackException();

        return top.getTile();
    }

    /**
     * Removes and returns the tile at the top of this stack
     * 
     * @return the removed tile
     * @throws EmptyStackException if this stack is empty
     */
    @Override
    public Tile pop() {
        if (isEmpty())
            throw new EmptyStackException();

        Tile tile = top.getTile();
        top = top.getNext();
        size--;
        return tile;
    }

    /**
     * Pushes the provided tile at top of this stack
     * 
     * @param tile an element to be added
     */
    @Override
    public void push(Tile tile) {
        top = new Node(tile, top);
        size++;
    }

    /**
     * Returns the size of this stack
     * 
     * @return the size of this stack
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an iterator to iterate through this stack starting from its top
     * 
     * @return Returns an iterator to iterate through this stack starting from its
     *         top
     */
    @Override
    public Iterator<Tile> iterator() {
        return new TileListIterator(top);
    }

}
