package battleship.models;

public class EmptySea extends Ship {

    public EmptySea() {
        this.length = 1;
    }

    @Override
    public boolean shootAt(int row, int column) {
        // This method overrides shootAt(int row, int column) that is inherited from Ship,
        // and always returns false to indicate that nothing was hit.
        this.hit[0] = true;

        return false;
    }

    @Override
    public boolean isSunk() {
        // This method overrides isSunk() that is inherited from Ship, and always returns false to indicate that you didn't sink anything.
        return false;
    }

    @Override
    public String toString() {
        // Returns a single-character String to use in the Oceans toString method (see below).
        return "-";

    }

    @Override
    public String getShipType() {
        return "Empty Sea";
    }

    @Override
    public int getLength() {
        return 1;
    }

}
