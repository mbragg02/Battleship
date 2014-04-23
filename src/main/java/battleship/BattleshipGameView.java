package battleship;

public interface BattleshipGameView {
    void welcomeMessage();

    void finalScore(int shotsFired);

    void printOcean(Ocean ocean);

    void exitMessage();

    void playAgain();

    void invalidOption();

    void enterShot();

    String invalidShot();

    String invalidCharacters();

    String invalidShotRange(int gridLength);
}
