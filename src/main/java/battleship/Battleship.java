package battleship;

/*
 * describes a ship with length 4.
 */
class Battleship extends Ship {
		
	public Battleship() {
		this.length = 4;
	}
	
	@Override
    public int getLength() {
		return this.length;
	}

	@Override
    public String getShipType() {
		return "Battleship";
	}
	

}
