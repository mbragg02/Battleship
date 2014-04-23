package battleship;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Game class
 * In this class you will:
 * - set up the game.
 * - accept shots from the user.
 * - display the results.
 * - print final scores.
 * - ask the user if s/he wants to play again.
 *
 * @author Michael Bragg
 */
class BattleshipGame {

    private final Scanner scanner;
    private boolean playAgain;
    private int gridLength;
    private BattleshipGameView view;

    public BattleshipGame(BattleshipGameView battleshipGameView) {
        this.scanner = new Scanner(System.in);
        this.view = battleshipGameView;
    }

    /**
     * Main battleship loop. Allows multiple games.
     */
    void launch() {
        Ocean ocean;
        while (true) {
            ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            gridLength = ocean.getShipArray().length;

            view.welcomeScreen();

            gameLoop(ocean);

            System.out.println(ocean);
            view.finalScore(ocean.getShotsFired());

            playAgain();
            if (!playAgain) {
                break;
            }
        }
        System.out.println("Goodbye");
        scanner.close();
    }

    /**
     * Game loop - Whilst there are still ships not sunk.
     *
     * @param ocean Ocean
     */
    private void gameLoop(Ocean ocean) {
        int[] coordinates;
        while (!ocean.isGameOver()) {
            System.out.println(ocean.toString());
            do {
                try {
                    coordinates = getUserCoordinates();
                    ocean.shootAt(coordinates[0], coordinates[1]);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (NoSuchElementException e) {
                    break;
                }
            } while (true);
        }
    }

    /**
     * Gets coordinates from the user. Throw exception if it's not valid
     *
     * @return int[] Array containing the row and column values.
     * @throws IllegalArgumentException if the entered coordinates are: Not digits, out of bounds of the game grid or not a valid format.
     */
    private int[] getUserCoordinates() throws IllegalArgumentException, NoSuchElementException {

        int[] coordinates = new int[2];

        System.out.println("Enter a row and column number to shoot at (e.g 3,5): ");
        String[] userInput = scanner.nextLine().split(",");

        if (userInput.length != 2) {
            throw new IllegalArgumentException("Invalid coordinates. Please try again");
        }

        try {
            coordinates[0] = Integer.parseInt(userInput[0].trim());
            coordinates[1] = Integer.parseInt(userInput[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coordinates must be digits. Please try again");
        }

        if (coordinates[0] > gridLength - 1 || coordinates[1] > gridLength - 1) {
            throw new IllegalArgumentException("Coordinates must be in the range 0 and " + (gridLength - 1) + " Please try again");
        }

        return coordinates;
    }

    /**
     * Asks the user wants to play again & sets play again variable.
     */
    private void playAgain() {
        while (true) {
            System.out.print("Would you like to play again? y or n: ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("y")) {
                playAgain = true;
                break;
            } else if (choice.equalsIgnoreCase("n")) {
                playAgain = false;
                break;
            } else {
                System.out.println("Not a valid option. Please try again.");
            }
        }
    }

}