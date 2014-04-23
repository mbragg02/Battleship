package battleship;

import battleship.models.AircraftCarrier;
import battleship.models.Ocean;
import battleship.models.Ship;
import org.junit.Before;
import org.junit.Test;

import static battleship.models.Ocean.GRID_SIZE;
import static org.junit.Assert.*;

public class OceanTest {

    private Ocean ocean;

    @Before
    public void setUp() throws Exception {
        this.ocean = new Ocean();
    }

    @Test
    public void testGetInitialShotsFired() {
        assertEquals(0, this.ocean.getShotsFired());
    }

    @Test
    public void testGetInitialHitCount() {
        assertEquals(0, this.ocean.getHitCount());
    }

    @Test
    public void testGetInitialShipsSunk() {
        assertEquals(0, this.ocean.getShipsSunk());
    }

    @Test
    public void testInitialIsGameOver() {
        assertFalse(this.ocean.isGameOver());
    }

    @Test
    public void testPlaceAllShipsRandomly() {

        ocean.placeAllShipsRandomly();
    }

    @Test
    public void testIsOccupiedEmptyGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                assertFalse(ocean.isOccupied(i,j));
            }
        }
    }

    @Test
    public void testIsOccupiedNonEmptyPositionHorizontal() {
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);
        for(int i = 0; i < testShip.length; i++) {
            assertTrue(ocean.isOccupied(0, i));
        }
    }

    @Test
    public void testIsOccupiedNonEmptyPositionVertical() {
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, false, ocean);
        for(int i = 0; i < testShip.length; i++) {
            assertTrue(ocean.isOccupied(i, 0));
        }
    }

    @Test
    public void testSizeOfGetShipArray() {
        Ship[][] grid = this.ocean.getShipArray();
        assertTrue(grid.length == 10);
        for (Ship[] aGrid : grid) {
            assertEquals(10, aGrid.length);
        }
    }

    @Test
    public void testOceanNewShipArray() {
        Ship[][] grid = this.ocean.getShipArray();
        for (Ship[] aGrid : grid) {
            for (int column = 0; column < grid.length; column++) {
                assertTrue(aGrid[column] instanceof Ship);
            }
        }
    }


    @Test
    public void testShootAtEmptySea() {
        boolean result = ocean.shootAt(0,0);
        assertEquals(1, this.ocean.getShotsFired());
        assertFalse(result);
    }

    @Test
    public void testShootAtShip() {
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);

        for (int i = 0; i < testShip.length; i++) {
            assertTrue(ocean.shootAt(0,i));
        }
    }

    @Test
    public void testShootAtShipSameLocation() {
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);

        // Should return true when shooting at the same location (when the ship is not sunk)
        for (int i = 0; i < testShip.length; i++) {
            assertTrue(ocean.shootAt(0,0));
        }
    }

    @Test
    public void testShootAtSunkenShip() {
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);

        for (int i = 0; i < testShip.length; i++) {
            assertTrue(ocean.shootAt(0,i));
        }

        // Shoot at all the positions of the sunken ship. Should return false
        for (int i = 0; i < testShip.length; i++) {
            assertFalse(ocean.shootAt(0, i));
        }
    }



    @Test
    public void testToString() {
        String actual = ocean.toString();
        String expected =
                  "! 0 1 2 3 4 5 6 7 8 9\n"
                + "0 . . . . . . . . . .\n"
                + "1 . . . . . . . . . .\n"
                + "2 . . . . . . . . . .\n"
                + "3 . . . . . . . . . .\n"
                + "4 . . . . . . . . . .\n"
                + "5 . . . . . . . . . .\n"
                + "6 . . . . . . . . . .\n"
                + "7 . . . . . . . . . .\n"
                + "8 . . . . . . . . . .\n"
                + "9 . . . . . . . . . .\n";

        assertEquals(expected, actual);
    }

}
