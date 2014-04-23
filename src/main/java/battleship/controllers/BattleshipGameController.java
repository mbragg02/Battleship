package battleship.controllers;

import battleship.models.Ocean;
import battleship.views.BattleshipGameView;

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
public class BattleshipGameController {

    private final Scanner scanner;
    private BattleshipGameView view;
    private Ocean ocean;

    public BattleshipGameController(BattleshipGameView battleshipGameView, Ocean ocean) {
        this.scanner = new Scanner(System.in);
        this.view = battleshipGameView;
        this.ocean = ocean;
    }

    /**
     * Main battleship loop. Allows multiple games.
     */
    public void launch() {

        view.welcomeMessage();

        while (true) {

            ocean.initialise();
            ocean.placeAllShipsRandomly();

            gameLoop(ocean);

            if (!playAgain()) {
                break;
            }
        }
        view.exitMessage();
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
            view.printOcean(ocean);
            do {
                try {
                    coordinates = getUserCoordinates();
                    ocean.shootAt(coordinates[0], coordinates[1]);
                    break;
                } catch (IllegalArgumentException e) {
                    view.printErrorMessage(e);
                } catch (NoSuchElementException e) {
                    break;
                }
            } while (true);
        }
        view.printOcean(ocean);
        view.finalScore(ocean.getShotsFired());
    }

    /**
     * Gets coordinates from the user. Throw exception if it's not valid
     *
     * @return int[] Array containing the row and column values.
     * @throws IllegalArgumentException if the entered coordinates are: Not digits, out of bounds of the game grid or not a valid format.
     */
    private int[] getUserCoordinates() throws IllegalArgumentException, NoSuchElementException {

        int[] coordinates = new int[2];

        view.enterShot();

        String[] userInput = scanner.nextLine().split(",");

        if (userInput.length != 2) {
            throw new IllegalArgumentException(view.invalidShot());
        }

        try {
            coordinates[0] = Integer.parseInt(userInput[0].trim());
            coordinates[1] = Integer.parseInt(userInput[1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(view.invalidCharacters());
        }

        if (coordinates[0] > Ocean.GRID_SIZE - 1 || coordinates[1] > Ocean.GRID_SIZE - 1) {
            throw new IllegalArgumentException(view.invalidShotRange(Ocean.GRID_SIZE - 1));
        }

        return coordinates;
    }

    /**
     * Asks the user wants to play again & sets play again variable.
     */
    private boolean playAgain() {
        boolean playAgain;
        while (true) {
            view.playAgain(view.userAffirmative(), view.userNegative());
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase(view.userAffirmative())) {
                playAgain = true;
                break;
            } else if (choice.equalsIgnoreCase(view.userNegative())) {
                playAgain = false;
                break;
            } else {
                view.invalidOption();
            }
        }
        return playAgain;
    }

}