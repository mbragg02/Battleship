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
}
