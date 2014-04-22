package battleship;

/**
 * This describes characteristics common to all the ships.
 * @author mbragg
 */
public abstract class Ship {

	private int bowColumn;
	private int bowRow;
	public int length;
	public boolean[] hit = {false, false, false, false, false};
	private boolean isHorizontal;

	/**
	 * This method exists only to be overridden.
	 * @return the length of this particular ship.
	 */
	abstract int getLength();
	
	/**
	 * @return int bow Row
	 */
    public int getBowRow() {
		return bowRow;
	}

	/**
	 * @return int bow Column
	 */
    public int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * @return boolean Horizontal
	 */
    public boolean isHorizontal() {
		return isHorizontal;
	}

	/**
	 * @param row int
	 */
    public void setBowRow(int row) {
		bowRow = row;
	}

	/**
	 * @param column int
	 */
    public void setBowColumn(int column) {
		bowColumn = column;
	}
	
	/**
	 * @param horizontal boolean
	 */
    public void setHorizontal(boolean horizontal) {
		isHorizontal = horizontal;
	}
	
	/**
	 * @return the type of this ship.
	 */
	abstract String getShipType();

	/**
	 * The ship must not overlap another ship, or touch another ship (vertically, horizontally, or diagonally), 
	 * and it must not stick out beyond the grid.
	 * @param row int
	 * @param column int
	 * @param horizontal boolean
	 * @param ocean Ocean
	 * @return returns true if it is okay to put a ship of this length with its bow at this location, 
	 * with the given orientation, and returns false otherwise.
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		Ship[][] grid = ocean.getShipArray();

		if (!(grid[row][column] instanceof EmptySea)) {
			return false;
		}

		if (this.shipsAreAdjacent(row, column, grid)) {
			return false;
		}

		if (horizontal) {
			if ((column + this.length) > grid.length - 1) {
				return false;
			}
			for (int i = 1; i < this.length; i++) {
				if (!(grid[row][column + i] instanceof EmptySea)) {
					return false;
				}
				if (this.shipsAreAdjacent(row, column + i, grid)) {
					return false;
				}
			}
		}
		else {
			if ((row + this.length) > grid.length - 1) {
				return false;
			}
			for (int i=1; i < this.length; i++) {
				if (!(grid[row+i][column] instanceof EmptySea)) {
					return false;
				}
				if (this.shipsAreAdjacent(row + i, column, grid)) {
					return false;
				}
			}
		}		

		return true;
	}

	/**
	 * 
	 * @param row int. The row of the center point to check.
	 * @param column int. The column of the center to check.
	 * @param grid Ship[][] The game grid.
	 * @return true if there are ships adjacent to the given ship. False otherwise
	 */
	private boolean shipsAreAdjacent(int row, int column, Ship[][] grid) {
		int[] increments = {-1, 0, 1};

        for (int incrementRow : increments) {
            for (int incrementColumn : increments) {

                int rowToTest = row + incrementRow;
                int columnToTest = column + incrementColumn;
                try {
                    if (!(grid[rowToTest][columnToTest] instanceof EmptySea)) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    // Its Ok if ship is positioned on the edge of the grid, so continue
                }
            }
        }

		return false;
	}

	/**
	 * Places the ship in the ocean.
	 * @param row it
	 * @param column int
	 * @param horizontal boolean
	 * @param ocean Ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);

		Ship[][] grid = ocean.getShipArray();

		int ship_length = this.getLength();

		for (int i = 0; i < ship_length; i++) {
			if(horizontal) {
				grid[row][column + i] = this;
			} else {		
				grid[row + i][column] = this;
			}	
		}
	}

	/**
	 * if a part of the ship occupies the given row and column, and the ship hasn't been sunk, 
	 * mark that part of the ship as hit (in the hit array, where index 0 indicates the bow) and return true, 
	 * otherwise return false.
	 * @param row int
	 * @param column int
	 * @return true if hit. false otherwise.
	 */
    public boolean shootAt(int row, int column) {
		if (this.isHorizontal) {
			if (this.bowRow != row) {
				return false;
			}
			for (int i=0; i < this.length; i++) {
				if ((this.bowColumn + i) == column) {
					this.registerHit(i);
					return true;
				}
			}
		}
		else {
			if (this.bowColumn != column) {
				return false;
			}
			for (int i=0; i < this.length; i++) {
				if ((this.bowRow + i) == row) {
					this.registerHit(i);
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * Record the hit in the hit array of the ship.
	 * @param index int
	 */
	private void registerHit(int index) {
		this.hit[index] = true;

		if (this.isSunk()) {
			System.out.println("You sank a " + this.getShipType() + "\n");
		}
	}
	
	/**
	 * @return true if every part of the ship has been hit, false otherwise.
	 */
    public boolean isSunk() {
		for (int i=0; i < this.length; i++) {
			if (!this.hit[i]) {
				return false;
			}
		}
		return true;
	}
	
	public String toString() {
		if (this.isSunk()) {
			return "x";
		}
		return "S";
	}

}
