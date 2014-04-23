package battleship;


/**
 * OODP 2014. Assignment Three: Battleship Game.
 * Main method - game launcher.
 */
public class BattleShipGame {

    public static void main(String[] args) {
        BattleshipGameView view = new BattleshipGameViewImpl();
        new BattleshipGameController(view).launch();
    }

}
