package battleship;

/*
 * describes a ship with length 1.
 */
public class PatrolBoat extends Ship {
	
	public PatrolBoat() {
		this.length = 1;
	}
	
	@Override
    public int getLength() {
		return this.length;
	}

	@Override
    public String getShipType() {
		return "Patrol Boat";
	}

}