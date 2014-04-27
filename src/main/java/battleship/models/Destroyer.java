package battleship.models;

/*
 * describes a ship with length 2.
 */
public class Destroyer extends Ship {

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