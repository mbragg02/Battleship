package battleship.models;

/*
 * describes a ship with length 3.
 */
public class Submarine extends Ship {
	
	public Submarine() {
		this.length = 3;
	}
	
	@Override
    public int getLength() {
		return this.length;
	}

	@Override
    public String getShipType() {
		return "Submarine";
	}
	

}
