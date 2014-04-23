package battleship;


/**
 * OODP 2014. Assignment Three: Battleship Game.
 * Main method - game launcher.
 */
public class BattleShipGameLauncher {

    public static void main(String[] args) {
        BattleshipGameView view = new BattleshipGameView();
        new BattleshipGame(view).launch();
    }

}
