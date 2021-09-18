import java.util.Random;

import java.util.ArrayList;

public class Game {
	private static String[] cards; // array storing a list of card names

	public static void initGame() {
		String[] cards = new String[3];
		cards[0] = "abc";
		cards[1] = "def";
	}

	public static void printCardNames() {
		for (int i = 0; i < cards.length; i++) {
			if (cards[i] != null) {
				System.out.print(cards[i].toUpperCase() + " ");
			}
		}
	}

	public static void main(String[] args) {
		Game.initGame();
		Game.printCardNames();
	}

}

// public class HelloWorld {

// public static void main(String[] args) {
// Random rand = new Random();
// int randValue = rand.nextInt(100);

// }
// }