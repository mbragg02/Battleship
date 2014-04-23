package battleship;

public interface BattleshipGameView {
    /**
     * displays a welcome message to the user
     */
    void welcomeMessage();

    /**
     * displays the final score the user achieved
     * @param shotsFired number of shots the user fired
     */
    void finalScore(int shotsFired);

    /**
     * for using the print ocean method utilised within the ocean class
     * @param ocean to be printed
     */
    void printOcean(Ocean ocean);

    /**
     * displays a message when closing the system
     */
    void exitMessage();

    /**
     * displays a message asking if a user would like to play again
     */
    void playAgain();

    /**
     * displayed when a user enters an invalid option when asked if they want to play again
     */
    void invalidOption();

    /**
     * requests a user to enter a shot in the formatted required for this version of the game
     */
    void enterShot();

    /**
     * Error message for exceptions when user enters the shot format incorrectly
     * @return invalidShot message
     */
    String invalidShot();

    /**
     * Error message for exceptions when user enters incompatible characters for a shot request
     * @return invalidCharacter message
     */
    String invalidCharacters();

    /**
     * Error message for exceptions when user shoots outside the range of the grid
     * @param gridLength length of current grid (usually minus 1, depending on implementation of grid)
     * @return invalidShotRange message
     */
    String invalidShotRange(int gridLength);
}
