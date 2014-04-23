package battleship;

public class BattleshipGameView {
    public void welcomeScreen() {
        System.out.println("Welcome to Battleships\n");
    }

    public void finalScore(int shotsFired) {
        System.out.println("Final score: " + shotsFired + " shots \n");
    }

    public void printOcean(Ocean ocean) {
        System.out.println(ocean);
    }

    public void goodBye() {
        System.out.println("Goodbye, thanks for playing!");
    }
}
