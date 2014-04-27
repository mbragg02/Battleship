package battleship.views;

import battleship.models.Ocean;

public class BattleshipGameViewImpl implements BattleshipGameView {

    private String boat = "\n" + "       _~" + "\n" + "    _~ )_)_~" + "\n" + "    )_))_))_)" + "\n" + "    _!__!__!_" + "\n" + "    " + "\\" + "______t/" + "\n" + "  ~~~~~~~~~~~~~" + "\n";


    @Override
    public void welcomeMessage() {
        System.out.println("Welcome to Battleships\n" + boat + "\n");
    }

    @Override
    public void finalScore(int shotsFired) {
        System.out.println("Final score: " + shotsFired + " shots \n");
    }

    @Override
    public void printOcean(Ocean ocean) {
        System.out.println(ocean);
    }

    @Override
    public void exitMessage() {
        System.out.println(boat + "\nGoodbye, thanks for playing!");
    }

    @Override
    public void playAgain(String affirmative, String negative) {
        System.out.print("Would you like to play again? enter '" + affirmative + "' or '" + negative + "': ");
    }

    @Override
    public void invalidOption() {
        System.out.println("Not a valid option. Please try again.");
    }

    @Override
    public void enterShot() {
        System.out.println("Enter a row and column number to shoot at (e.g 3,5): ");
    }

    @Override
    public String invalidShot() {
        return "Invalid coordinates. Please try again";
    }

    @Override
    public String invalidCharacters() {
        return "Coordinates must be digits. Please try again";
    }

    @Override
    public String invalidShotRange(int gridLength) {
        return "Coordinates must be in the range 0 and " + gridLength + " Please try again";
    }

    @Override
    public String userAffirmative() {
        return "y";
    }

    @Override
    public String userNegative() {
        return "n";
    }

    @Override
    public void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
