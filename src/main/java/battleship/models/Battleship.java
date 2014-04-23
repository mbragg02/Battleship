package battleship.models;

/*
 * describes a ship with length 4.
 */
public class Battleship extends Ship {
		
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
