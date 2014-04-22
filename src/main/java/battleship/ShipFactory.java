package battleship;

public class ShipFactory {
	
	private static ShipFactory shipFactory;
	
	private ShipFactory() {
		// Empty private constructor;
	}
	
	public static ShipFactory getInstance() {
		if (shipFactory == null) {
			shipFactory = new ShipFactory();
		}
		return shipFactory;
	}
	
	public Ship getAircraftCarrier() {
		return new AircraftCarrier();
	}
	
	public Ship getBattleShip() {
		return new Battleship();
	}
	
	public Ship getDestroyer() {
		return new Destroyer();
	}

	public Ship getEmptySea() {
		return new EmptySea();
	}
	
	public Ship getPatrolBoat() {
		return new PatrolBoat();
	}
	
	public Ship getSubmarine() {
		return new Submarine();
	}
}
