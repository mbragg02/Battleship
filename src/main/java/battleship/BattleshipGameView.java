package battleship;

public class BattleshipGameView {
    public void welcomeMessage() {
        System.out.println("Welcome to Battleships\n");
    }

    public void finalScore(int shotsFired) {
        System.out.println("Final score: " + shotsFired + " shots \n");
    }

    public void printOcean(Ocean ocean) {
        System.out.println(ocean);
    }

    public void exitMessage() {
        System.out.println("Goodbye, thanks for playing!");
    }

    public void playAgain() {
        System.out.print("Would you like to play again? y or n: ");
    }

    public void invalidOption() {
        System.out.println("Not a valid option. Please try again.");
    }

    public void enterShot() {
        System.out.println("Enter a row and column number to shoot at (e.g 3,5): ");
    }

    String invalidShot() {
        return "Invalid coordinates. Please try again";
    }
}
