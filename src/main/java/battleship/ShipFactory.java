package battleship;

public class ShipFactory {
	
	private static ShipFactory ship_factory;
	
	private ShipFactory() {
		// Empty private constructor;
	}
	
	public static ShipFactory getInstance() {
		if (ship_factory == null) {
			ship_factory = new ShipFactory();
		}
		return ship_factory;
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
