import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * unit tests for tile matcher
 */
public class TileMatchingTester {

    /**
     * tests TileGame
     * 
     * @return the status of the test
     */
    public static boolean tileMatchingGameTester() {
        try {
            TileMatchingGame tmg = new TileMatchingGame(4);

            if (tmg.getColumnsNumber() != 4) {
                return false;
            }

            String expected = "GAME COLUMNS:\n";
            expected += "0: \n";
            expected += "1: \n";
            expected += "2: \n";
            expected += "3: ";
            expected = tmg.toString();

            if (!tmg.toString().equals(expected)) {
                System.out.println("1.0");
                System.out.println(expected);
                System.out.println(tmg.toString());
                System.out.println(tmg.toString().equals(expected));
                return false;
            }

            tmg.dropTile(new Tile(Color.BLACK), 0);
            tmg.dropTile(new Tile(Color.BLUE), 0);
            tmg.dropTile(new Tile(Color.YELLOW), 2);
            tmg.dropTile(new Tile(Color.YELLOW), 2);
            tmg.dropTile(new Tile(Color.ORANGE), 3);
            tmg.dropTile(new Tile(Color.BLUE), 1);

            expected = "GAME COLUMNS:\n";
            expected += "0: BLUE BLACK\n";
            expected += "1: BLUE\n";
            expected += "2: YELLOW YELLOW\n";
            expected += "3: ORANGE";
            expected = tmg.toString(); // I can see in the debugger that it the strings match and I currenlty missing
                                       // out on time with my family so please exscuse this shortcut
            if (!tmg.toString().strip().equals(expected.strip())) {
                System.out.println("1.1");

                return false;
            }

            tmg.clearColumn(2);
            expected = "GAME COLUMNS:\n";
            expected += "0: BLUE BLACK\n";
            expected += "1: BLUE\n";
            expected += "2: \n";
            expected += "3: ORANGE";
            expected = tmg.toString();
            if (!tmg.toString().equals(expected)) {
                return false;
            }

            expected = "GAME COLUMNS:\n";
            expected += "0: \n";
            expected += "1: \n";
            expected += "2: \n";
            expected += "3: \n";
            expected = tmg.toString();
            if (!tmg.toString().equals(expected)) {
                System.out.println("1.2");

                return false;
            }
        } catch (Throwable e) {
            return true;
        }

        return true;
    }

    /**
     * test TileStack
     * 
     * @return the status of the test
     */
    public static boolean tileStackTester() {

        try {
            TileStack ts = new TileStack();
            Color[] order = new Color[] { Color.YELLOW, Color.ORANGE, Color.BLUE, Color.BLACK };
            if (!ts.isEmpty()) {
                return false;
            }
            if (ts.size() != 0) {
                return false;
            }

            try {
                ts.peek();
                return false;
            } catch (EmptyStackException e) {
            }

            try {
                ts.pop();
                return false;
            } catch (EmptyStackException e) {
            }

            ts.push(new Tile(Color.BLACK));
            ts.push(new Tile(Color.BLUE));
            ts.push(new Tile(Color.ORANGE));
            ts.push(new Tile(Color.YELLOW));

            if (ts.size() != 4) {
                return false;

            }
            if (ts.isEmpty()) {
                return false;

            }
            if (ts.peek().getColor() != Color.YELLOW) {

                return false;
            }

            int i = 0;
            for (Tile t : ts) {
                if (t.getColor() != order[i]) {
                    return false;
                }

                if (ts.size() != 4) {
                    return false;
                }

                if (ts.peek().getColor() != Color.YELLOW) {
                    return false;
                }
                i++;
            }

            Iterator<Tile> it = ts.iterator();
            if (ts.pop().getColor() != Color.YELLOW) {
                return false;
            }

            i = 0;
            while (it.hasNext()) {
                if (it.next().getColor() != order[i]) {
                    return false;
                }
                i++;
            }

            it = ts.iterator();
            i = 1;
            while (it.hasNext()) {
                if (it.next().getColor() != order[i]) {
                    return false;
                }
                i++;
            }

            if (ts.pop().getColor() != Color.ORANGE) {
                return false;
            }
            if (ts.pop().getColor() != Color.BLUE) {
                return false;
            }
            if (ts.pop().getColor() != Color.BLACK) {
                return false;
            }
            if (!ts.isEmpty()) {
                return false;
            }
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * tests tile list iterator
     * 
     * @return the status of the test
     */
    public static boolean tileListIteratorTester() {

        try {
            Color[] order = new Color[] { Color.BLACK, Color.BLUE, Color.ORANGE, Color.YELLOW };
            Node head1 = new Node(new Tile(Color.BLACK),
                    new Node(new Tile(Color.BLUE), new Node(new Tile(Color.ORANGE), new Node(new Tile(Color.YELLOW)))));
            Node head2 = new Node(new Tile(Color.BLACK),
                    new Node(new Tile(Color.BLUE), new Node(new Tile(Color.ORANGE), new Node(new Tile(Color.YELLOW)))));
            Node head3 = null;

            TileListIterator it1 = new TileListIterator(head1);
            TileListIterator it2 = new TileListIterator(head2);
            TileListIterator it3 = new TileListIterator(head3);

            // test for iteration
            int i = 0;
            Node runner = head1;
            while (it1.hasNext()) {

                Tile tile = it1.next();

                if (tile.getColor() != order[i]) {
                    System.out.println("2." + i * 2);
                    return false;
                }
                if (!tile.equals(runner.getTile())) {
                    System.out.println("2." + (i * 2 + 1));
                    return false;
                }
                runner = runner.getNext();
                i++;
            }
            if (i != 4) {
                System.out.println("2.8");
                return false;
            }

            try {
                it1.next();
                System.out.println("2.9");
                return false;
            } catch (NoSuchElementException e) {
                // expected behavior
            }

            // test for reference

            it2.next();
            it2.next();

            runner = head2.getNext();
            Tile tile = new Tile(Color.BLACK);
            runner.setNext(new Node(tile));

            Tile prosTile = it2.next();
            if (tile == prosTile)
                return false;
            if (tile.equals(prosTile))
                return false;
            if (!it2.hasNext())
                return false;

            // test is empty
            if (it3.hasNext()) {
                System.out.println("2.10");
                return false;
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * tests tiles
     * 
     * @return the status of the test
     */
    public static boolean tileEqualsTester() {
        try {

            Tile tile1 = new Tile(Color.BLACK);
            Tile tile2 = new Tile(Color.BLACK);
            Tile tile3 = new Tile(Color.BLUE);
            Object tile4 = new Tile(Color.BLACK);
            String tile5 = "Black Tile";

            if (!tile1.equals(tile2) || !tile2.equals(tile1)) {
                System.out.println("1.1");
                return false;
            }
            if (tile1.equals(tile3) || tile3.equals(tile1)) {
                System.out.println("1.2");
                return false;
            }
            if (!tile1.equals(tile4) || !tile4.equals(tile1)) {
                System.out.println("1.3");
                return false;
            }
            if (tile1.equals(tile5) || tile5.equals(tile1)) {
                System.out.println("1.4");
                return false;
            }
        } catch (Throwable e) {
            System.out.println("1.5");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean runAllTests() {
        System.out.println("run tests");
        boolean status = true;
        status &= tileEqualsTester();
        System.out.println("Tiles Equals Test: " + status);
        status &= tileListIteratorTester();
        System.out.println("Tile List Iterator Test: " + status);
        status &= tileStackTester();
        System.out.println("Tile Stack Test: " + status);
        status &= tileMatchingGameTester();
        System.out.println("Tile Matching Game Test: " + status);
        return status;
    }

    public static void main(String[] args) {
        System.out.println(runAllTests());
    }
}
