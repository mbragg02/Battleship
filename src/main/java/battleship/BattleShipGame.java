package battleship;


import battleship.controllers.BattleshipGameController;
import battleship.controllers.BattleshipGameControllerImpl;
import battleship.models.Ocean;
import battleship.views.BattleshipGameView;
import battleship.views.BattleshipGameViewImpl;

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

        BattleshipGameController controller = new BattleshipGameControllerImpl(view, ocean);
        controller.launch();
    }


}
