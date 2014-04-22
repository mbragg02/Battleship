package battleship;

/*
 * describes a ship with length 5.
 */
public class AircraftCarrier extends Ship {
		
	public AircraftCarrier() {
		this.length = 5;
	}

	@Override
    public int getLength() {
		return this.length;
	}

	@Override
    public String getShipType() {
		return "Aircraft Carrier";
	}
}
