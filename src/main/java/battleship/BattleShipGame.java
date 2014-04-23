package battleship;


/**
 * OODP 2014. Assignment Three: Battleship Game.
 * Main method - game launcher.
 */
public class BattleShipGame {

    public static void main(String[] args) {
        BattleShipGame bsg = new BattleShipGame();
        bsg.launch();
    }

    private void launch() {
        BattleshipGameView view = new BattleshipGameViewImpl();
        Ocean ocean = new Ocean();

        BattleshipGameController controller = new BattleshipGameController(view, ocean);
        controller.launch();
    }


}
