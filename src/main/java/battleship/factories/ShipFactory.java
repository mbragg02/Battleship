package battleship.factories;

import battleship.models.*;

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

    public Ship getShip(String name) {
        switch (name) {
            case "aircraftCarrier":
                return new AircraftCarrier();
            case "battleship":
                return new Battleship();
            case "destroyer":
                return new Destroyer();
            case "patrolBoat":
                return new PatrolBoat();
            case "submarine":
                return new Submarine();
            case "emptySea":
                return new EmptySea();
            default:
                return new EmptySea();
        }
    }
}
