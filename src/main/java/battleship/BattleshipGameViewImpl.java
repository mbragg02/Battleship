package battleship;

public class BattleshipGameViewImpl implements BattleshipGameView {
    @Override
    public void welcomeMessage() {
        System.out.println("Welcome to Battleships\n");
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
        System.out.println("Goodbye, thanks for playing!");
    }

    @Override
    public void playAgain() {
        System.out.print("Would you like to play again? y or n: ");
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
}
