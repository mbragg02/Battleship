package battleship.models;

import battleship.factories.ShipFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This contains a 10x10 grid of Ships which represents the "ocean", and some methods to manipulate it.
 * @author mbragg
 */
public class Ocean {

    private final Ship[][] gameBoard;
    private final boolean[][] locationsFiredUpon;

    private List<Ship> ships;
    private int shotsFired;
    private int hitsRecorded;
    private int shipsSunk;

    public static final int GRID_SIZE = 10;
    private static final int AIRCRAFT_NO = 1;
    private static final int BATTLE_NO   = 2;
    private static final int SUB_NO 	 = 2;
    private static final int DESTROY_NO  = 2;
    private static final int PATROL_NO   = 4;
    private final ShipFactory shipFactory;
    private int totalShips;

    /**
     * Creates an empty ocean (fills the ships array with EmptySeas).
     * Also initializes any game variables, such as how many shots have been fired.
     */
    public Ocean() {

        shipFactory = ShipFactory.getInstance();
        this.gameBoard = new Ship[GRID_SIZE][GRID_SIZE];
        this.locationsFiredUpon = new boolean[GRID_SIZE][GRID_SIZE];

    }

    /**
     * Fill game board with EmptySea() and fill locationsFiredUpon with false
     */
    private void initializeShipArray() {

        for (int row= 0; row < this.gameBoard.length; row++) {
            for (int col= 0; col < this.gameBoard[row].length; col++) {
                this.gameBoard[row][col] = shipFactory.getEmptySea();
                this.locationsFiredUpon[row][col] = false;
            }
        }
    }

    /**
     * Construct the fleet of ships
     * The fleet consists of one aircraft carrier, two battleships, two submarines, two destroyers, and four patrol boats.
     */
    private void buildShips() {
        ships = new ArrayList<>();

        for (int i = 0; i < AIRCRAFT_NO; i++) {
            ships.add(shipFactory.getAircraftCarrier());
            totalShips++;
        }
        for (int i = 0; i < BATTLE_NO; i++) {
            ships.add(shipFactory.getBattleShip());
            totalShips++;
        }
        for (int i = 0; i < SUB_NO; i++) {
            ships.add(shipFactory.getSubmarine());
            totalShips++;
        }
        for (int i = 0; i < DESTROY_NO; i++) {
            ships.add(shipFactory.getDestroyer());
            totalShips++;
        }
        for (int i = 0; i < PATROL_NO; i++) {
            ships.add(shipFactory.getPatrolBoat());
            totalShips++;
        }
    }

    /**
     * Place all the ships randomly on the(initially empty) ocean.
     * Place larger ships before smaller ones, or you may end up with no legal place to put a large ship.
     */
    public void placeAllShipsRandomly() {
        boolean valid_position = false;
        int row = 0;
        int column = 0;
        boolean horizontal = false;
        Random random = new Random();

        for (Ship ship : ships) {
            while (!valid_position) {
                row = random.nextInt(GRID_SIZE);
                column = random.nextInt(GRID_SIZE);
                horizontal = random.nextBoolean();
                valid_position = ship.okToPlaceShipAt(row, column, horizontal, this);
            }
            ship.placeShipAt(row, column, horizontal, this);
            valid_position = false;
        }
    }


    /**
     * Check if a given location contains a ship
     * @param row int
     * @param column int
     * @return true if the given location contains a ship, false if it does not.
     */
    public boolean isOccupied(int row, int column) {
        return !(this.gameBoard[row][column] instanceof EmptySea);
    }

    /**
     * Shoot at a given position.
     * In addition, this method updates the number of shots that have been fired, and the number of hits.
     * Note: If a location contains a real ship, shootAt should return true every time the user shoots at that same location.
     * Once a ship has been sunk, additional shots at its location should return false.
     * @param row int
     * @param column int
     * @return true if the given location contains a real ship, still afloat, (not an EmptySea), false if it does not.
     */
    public boolean shootAt(int row, int column) {
        this.shotsFired++;
        this.locationsFiredUpon[row][column] = true;

        boolean isOccupied = isOccupied(row, column);

        Ship shipAtPosition = this.gameBoard[row][column];

        // Once a ship has been sunk, additional shots at its location should return false.
        if (shipAtPosition.isSunk()) {
            return false;
        }

        // True for a hit, false for a miss
        boolean shipHit = shipAtPosition.shootAt(row, column);

        if (isOccupied) {
            // contains a ship

            if (shipHit) {
                this.hitsRecorded++;
            }

            if (shipAtPosition.isSunk()) {
                shipsSunk++;
            }
        }

        // else empty sea; return false

        return shipHit;
    }

    /**
     * @return the number of shots fired (in this game).
     */
    public int getShotsFired() {
        return this.shotsFired;
    }

    /**
     * @return  the number of hits recorded (in this game). All hits are counted, not just the first time a given square is hit.
     */
    public int getHitCount() {
        return this.hitsRecorded;
    }

    /**
     * @return the number of ships sunk (in this game).
     */
    public int getShipsSunk() {
        return this.shipsSunk;
    }

    /**
     * @return true if all the ships have been sunk, otherwise false.
     */
    public boolean isGameOver() {
        return (this.shipsSunk == totalShips);
    }

    /**
     * @return the grid of ships.
     */
    public Ship[][] getShipArray() {
        return gameBoard;
    }

    /**
     * @return a string representing the ocean.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        int rowCount = 0;
        int columnCount;
        String newLine = "\n";
        String blank = " ";

        // Write board column headers
        result.append("!");

        for (columnCount = 0; columnCount < gameBoard.length; columnCount++) {
            result.append(blank).append(columnCount);
        }
        result.append(newLine);

        for (Ship[] row : gameBoard) {

            result.append(rowCount);
            columnCount = 0;
            for (Ship column : row) {
                if (this.locationsFiredUpon[rowCount][columnCount]) {
                    result.append(blank).append(column.toString());
                }
                else {
                    result.append(blank).append(".");
                }
                ++columnCount;
            }
            ++rowCount;
            result.append(newLine);
        }
        return result.toString();
    }

    /**
     *
     *
     */
    public void initialise() {
        this.shotsFired = 0;
        this.hitsRecorded = 0;
        this.shipsSunk = 0;
        totalShips = 0;

        initializeShipArray();
        buildShips();
    }
}
