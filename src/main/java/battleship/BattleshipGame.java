package battleship;

import java.util.Scanner;

/**
 * This is the main class, containing the main method and a variable of type Ocean.
 * In this class you will:
 * - set up the game.
 * - accept shots from the user.
 * - display the results.
 * - print final scores.
 * - ask the user if s/he wants to play again.
 * @author mbragg
 *
 */
class BattleshipGame {

	private Scanner scanner;
	private boolean play_again;
	private int grid_length;

	public static void main(String[] args) {
		new BattleshipGame().launch();
	}

	/**
	 * Main battleship loop. Allows multiple games.
	 */
	private void launch() {
		scanner = new Scanner(System.in);

		while(true) {
			Ocean ocean = new Ocean();
			ocean.placeAllShipsRandomly();
			grid_length = ocean.getShipArray().length;
			
			System.out.println("Welcome to Battleships\n");
			
			game_loop(ocean);
			
			System.out.println(ocean.toString());
			System.out.println("Final score: " + ocean.getShotsFired() + " shots \n");

			playAgain();
			if (!play_again) {
				break;
			}
		}
		System.out.println("Goodbye");
		scanner.close();
	}
	
	/**
	 * Game loop - Whilst there are still ships not sunk.
	 * @param ocean Ocean
	 */
	private void game_loop(Ocean ocean) {
		int[] coordinates;
		while (!ocean.isGameOver()) {
			System.out.println(ocean.toString());
			do {
				try {
					coordinates = get_user_coordinates();
					break;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} 
			} while(true);
			ocean.shootAt(coordinates[0], coordinates[1]);
		}
	}

	/**
	 * Gets coordinates from the user. Throw exception if it's not valid
	 * @return int[] Array containing the row and column values.
	 * @throws IllegalArgumentException if the entered coordinates are: Not digits, out of bounds of the game grid or not a valid format.
	 */
	private int[] get_user_coordinates() throws IllegalArgumentException {

		int[] coordinates = new int[2];

			System.out.println("Enter a row and column number to shoot at (e.g 3,5): ");
			String[] user_input = scanner.nextLine().split(",");
			if (user_input.length != 2) {
				throw new IllegalArgumentException("Invalid coordinates. Please try again");
			}
			
			try {
				coordinates[0] = Integer.parseInt(user_input[0].trim());
				coordinates[1] = Integer.parseInt(user_input[1].trim());
			} catch(NumberFormatException e) {
				throw new IllegalArgumentException("Coordinates must be digits. Please try again");
			}

			if (coordinates[0] > grid_length - 1 || coordinates[1] > grid_length - 1) {
				throw new IllegalArgumentException("Coordinates must be in the range 0 and " + (grid_length -1) + " Please try again");
			}

		return coordinates;
	}

	/**
	 * Asks the user wants to play again & sets play again variable.
	 */
	private void playAgain() {
		while(true){
			System.out.print("Would you like to play again? y or n: ");
			String choice = scanner.nextLine();

			if(choice.equalsIgnoreCase("y")) {
				play_again = true;
				break;
			} else if (choice.equalsIgnoreCase("n")) {
				play_again = false;
				break;
			} else {
				System.out.println("Not a valid option. Please try again.");
			}
		}
	}

}