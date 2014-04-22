package battleship;

/*
 * describes a ship with length 2.
 */
class Destroyer extends Ship {
	
	public Destroyer() {
		this.length = 2;
	}
	@Override
    public int getLength() {
		return this.length;
	}

	@Override
    public String getShipType() {
		return "Destroyer";
	}

}