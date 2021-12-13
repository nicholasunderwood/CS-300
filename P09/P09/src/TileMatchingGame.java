import java.util.Iterator;

/**
 * Base operations for a tile matching game
 */
public class TileMatchingGame {

    private TileStack[] columns;

    /**
     * Creates a new matching tile game with a given number of columns and
     * initializes its contents. A new matching tile game stores an empty TileStack
     * at each of its columns.
     * 
     * @param coloumCount number of columns in this game
     * @throws IllegalArgumentException if columnNumber is less or equal to zero
     */
    TileMatchingGame(int coloumCount) {
        if (coloumCount <= 0)
            throw new IllegalArgumentException("columnNumber is less or equal to zero");
        columns = new TileStack[coloumCount];

        for (int i = 0; i < coloumCount; i++) {
            columns[i] = new TileStack();
        }

    }

    /**
     * Removes all the tiles from a column with a given index
     * 
     * @param index index of the column to clear
     * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of
     *                                   the columns of this game
     */
    public void clearColumn(int index) {
        if (index < 0 || index >= columns.length)
            throw new IndexOutOfBoundsException("Index is out of bounds");

        while (!columns[index].isEmpty()) {
            columns[index].pop();
        }
    }

    /**
     * Returns a string representation of the stack of tiles at a given column
     * index, and an empty string "" if the stack is empty.
     * 
     * @param index the index of a column in this game
     * @return a string representation of the column at a given index of this game
     * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of
     *                                   the columns of this game
     */
    public String column(int index) {
        String str = "";
        Iterator<Tile> it = columns[index].iterator();
        while (it.hasNext()) {
            
            str += it.next().toString() + ' ';
        }
        return str;
    }

    /**
     * Drops a tile at the top of the stack located at the given column index. If
     * tile will be dropped at the top of an equal tile (of same color), both tiles
     * will be removed from the stack of tiles at column index.
     * 
     * @param tile  a tile to drop
     * @param index column position of the stack of tiles where tile will be dropped
     * @throws IndexOutOfBoundsException if index is out of bounds of the indexes of
     *                                   the columns of this game
     */
    public void dropTile(Tile tile, int index) {
        if (index < 0 || index >= columns.length)
            throw new IndexOutOfBoundsException("Index is out of bounds");


        columns[index].push(tile);
    }

    /**
     * Gets the number of columns in this tile matching game
     * 
     * @return the number of columns in this tile matching game
     */
    public int getColumnsNumber() {
        return columns.length;
    }

    /**
     * Restarts the game. All stacks of tiles in the different columns of this game
     * will be empty
     */
    public void restartGame() {
        for (int i = 0; i < columns.length; i++) {
            columns[i] = new TileStack();
        }
    }

    /**
     * Returns a string representation of this tile matching game
     * 
     * @returns a string representation of this tile matching game
     * @overrides toString in class java.lang.Object
     */
    @Override
    public String toString() {
        String str = "GAME COLUMNS:\n";

        for (int i = 0; i < columns.length; i++) {
            String col = column(i);
            str += i + ": " + col + "\n";
        }

        return str.strip();
    }
}
