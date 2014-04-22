package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This contains a 10x10 grid of Ships which represents the "ocean", and some methods to manipulate it.
 * @author mbragg 
 */
public class Ocean {

	private final Ship[][] game_board;
	private final boolean[][] locations_fired_upon;

	private List<Ship> ships;
	private int shots_fired;	
	private int hits_recorded;
	private int ships_sunk;

	private static final int GRID_SIZE = 10;
	private static final int AIRCRAFT_NO = 1;
	private static final int BATTLE_NO   = 2;
	private static final int SUB_NO 	 = 2;
	private static final int DESTROY_NO  = 2;
	private static final int PATROL_NO   = 4;

	/**
	 * Creates an empty ocean (fills the ships array with EmptySeas). 
	 * Also initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		this.game_board = new Ship[GRID_SIZE][GRID_SIZE];
		this.locations_fired_upon = new boolean[GRID_SIZE][GRID_SIZE];

		this.shots_fired = 0;
		this.hits_recorded = 0;
		this.ships_sunk = 0;
		
		initializeShipArray();
		buildShips();
	}
	
	/**
	 * Fill game board with EmptySea() and fill locations_fired_upon with false
	 */
	private void initializeShipArray() {		
		for (int row= 0; row < this.game_board.length; row++) {
			for (int col=  0; col < this.game_board[row].length; col++) {
				this.game_board[row][col] = new EmptySea();
				this.locations_fired_upon[row][col] = false;
			}
		}
	}

	/**
	 * Place all the ships randomly on the(initially empty) ocean. 
	 * Place larger ships before smaller ones, or you may end up with no legal place to put a large ship. 
	 */
	void placeAllShipsRandomly() {
		boolean valid_position = false;
		int row = 0;
		int column = 0;
		boolean horizontal = false;
		Random random = new Random();

		for (Ship ship : ships) {
			while (!valid_position) {
				row = random.nextInt(10);
				column = random.nextInt(10);
				horizontal = random.nextBoolean();
				valid_position = ship.okToPlaceShipAt(row, column, horizontal, this);				
			}
			ship.placeShipAt(row, column, horizontal, this);
			valid_position = false;
		}	
	}
	

	/**
	 * Construct the fleet of ships
	 * The fleet consists of one aircraft carrier, two battleships, two submarines, two destroyers, and four patrol boats.
	 */
	private void buildShips() {
		ships = new ArrayList<>();
		
		ShipFactory factory = ShipFactory.getInstance();

		for (int i = 0; i < AIRCRAFT_NO; i++) {
			ships.add(factory.getAircraftCarrier());
		}
		for (int i = 0; i < BATTLE_NO; i++) {
			ships.add(factory.getBattleShip());
		}
		for (int i = 0; i < SUB_NO; i++) {
			ships.add(factory.getSubmarine());
		}
		for (int i = 0; i < DESTROY_NO; i++) {
			ships.add(factory.getDestroyer());
		}
		for (int i = 0; i < PATROL_NO; i++) {
			ships.add(factory.getPatrolBoat());
		}	
	}
	
	/**
	 * Check if a given location contains a ship
	 * @param row int
	 * @param column int
	 * @return true if the given location contains a ship, false if it does not.
	 */
	boolean isOccupied(int row, int column) {
		return !(this.game_board[row][column] instanceof EmptySea);
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
	boolean shootAt(int row, int column) {
		this.shots_fired++;
		this.locations_fired_upon[row][column] = true;

		Ship ship_at_position = this.game_board[row][column];		

		if (ship_at_position.isSunk()) {
			return false;
		}

		boolean ship_hit = ship_at_position.shootAt(row, column);

		if (ship_hit) {
			this.hits_recorded++;
		}
		
		if (ship_at_position instanceof EmptySea) {
			return ship_hit;
		}
		
		if (ship_at_position.isSunk()) {
			ships_sunk++;
		}
		return ship_hit;
	}

	/**
	 * @return the number of shots fired (in this game).
	 */
    public int getShotsFired() {
		return this.shots_fired;
	}

	/**
	 * @return  the number of hits recorded (in this game). All hits are counted, not just the first time a given square is hit.
	 */
    public int getHitCount() {
		return this.hits_recorded;
	}

	/**
	 * @return the number of ships sunk (in this game).
	 */
    public int getShipsSunk() {
		return this.ships_sunk;
	}

	/**
	 * @return true if all the ships have been sunk, otherwise false.
	 */
    public boolean isGameOver() {
		return (this.ships_sunk == 10);
	}

	/**
	 * @return the grid of ships.
	 */
    public Ship[][] getShipArray() {
		return game_board;
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
		for (columnCount = 0; columnCount < game_board.length; columnCount++) {
			result.append(blank).append(columnCount);
		}
		result.append(newLine);

		for (Ship[] row : game_board) {
			result.append(rowCount);
			columnCount = 0;
			for (Ship column : row) {	
				if (this.locations_fired_upon[rowCount][columnCount]) {
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
}
