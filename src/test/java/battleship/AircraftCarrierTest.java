package battleship;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AircraftCarrierTest {
	
	private AircraftCarrier aircraft_carrier;

    private Ocean ocean;
//    @Mock
//    private Ship[][] grid;
//    @Mock
//    private EmptySea emptySea;
//    @Mock
//    private Battleship battleShip;

	@Before
	public void setUp() throws Exception {
        ocean = new Ocean();


		aircraft_carrier = new AircraftCarrier();

        aircraft_carrier.setBowColumn(0);
        aircraft_carrier.setBowRow(0);
        aircraft_carrier.setHorizontal(true);
	}

    // Tests for AircraftCarrier subclass
	@Test
	public void testGetLength() {
		assertEquals(5, aircraft_carrier.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Aircraft Carrier", aircraft_carrier.getShipType());
	}


    // Tests for Ship superclass
    @Test
    public void testGetBowRow() {
        assertEquals(0, aircraft_carrier.getBowRow());
    }

    @Test
    public void aircraft_carrier() {
        assertEquals(0, aircraft_carrier.getBowColumn());
    }

    @Test
    public void testIsHorizontal() {
        assertTrue(aircraft_carrier.isHorizontal());
    }

    @Test
    public void testSetBowRow() {
        aircraft_carrier.setBowRow(3);
        assertEquals(3, aircraft_carrier.getBowRow());
    }

    @Test
    public void testSetBowColumn() {
        aircraft_carrier.setBowColumn(4);
        assertEquals(4, aircraft_carrier.getBowColumn());
    }

    @Test
    public void testSetHorizontal() {
        aircraft_carrier.setHorizontal(false);
        assertFalse(aircraft_carrier.isHorizontal());
    }

    @Test
    public void testIsNotSunk() {
        Assert.assertFalse(aircraft_carrier.isSunk());
    }

    @Test
    public void testIsSunk() {
        aircraft_carrier.hit[0] = true;
        aircraft_carrier.hit[1] = true;
        aircraft_carrier.hit[2] = true;
        aircraft_carrier.hit[3] = true;
        aircraft_carrier.hit[4] = true;
        assertTrue(aircraft_carrier.isSunk());
    }


    @Test
    public void testToStringNotSunk() {

        assertEquals("S", aircraft_carrier.toString());
    }

    @Test
    public void testToStringSunk() {
        aircraft_carrier.hit[0] = true;
        aircraft_carrier.hit[1] = true;
        aircraft_carrier.hit[2] = true;
        aircraft_carrier.hit[3] = true;
        aircraft_carrier.hit[4] = true;

        assertEquals("x", aircraft_carrier.toString());

    }

    @Test
    public void testShootAtVerticalShip() {
        aircraft_carrier.setHorizontal(false);

        assertTrue(aircraft_carrier.shootAt(0, 0));
        assertTrue(aircraft_carrier.shootAt(1, 0));
        assertTrue(aircraft_carrier.shootAt(2, 0));
        assertTrue(aircraft_carrier.shootAt(3, 0));
        assertTrue(aircraft_carrier.shootAt(4, 0));
    }

    @Test
    public void testShootAtVerticalShipMiss() {
        aircraft_carrier.setHorizontal(false);

        assertTrue(aircraft_carrier.shootAt(0, 0));
        assertFalse(aircraft_carrier.shootAt(0, 1));
        assertFalse(aircraft_carrier.shootAt(0, 2));
        assertFalse(aircraft_carrier.shootAt(0, 3));
        assertFalse(aircraft_carrier.shootAt(0, 4));
    }

    @Test
    public void testShootAtHorizontalShip() {
        // Ship is horizontal
        assertTrue(aircraft_carrier.shootAt(0, 0));
        assertTrue(aircraft_carrier.shootAt(0, 1));
        assertTrue(aircraft_carrier.shootAt(0, 2));
        assertTrue(aircraft_carrier.shootAt(0, 3));
        assertTrue(aircraft_carrier.shootAt(0, 4));

    }

    @Test
    public void testShootAtHorizontalShipMiss() {
        // Ship is horizontal
        assertTrue(aircraft_carrier.shootAt(0, 0));
        assertFalse(aircraft_carrier.shootAt(1, 0));
        assertFalse(aircraft_carrier.shootAt(2, 0));
        assertFalse(aircraft_carrier.shootAt(3, 0));
        assertFalse(aircraft_carrier.shootAt(4, 0));
    }


    @Test
    public void testOkToPlaceShipAt() {
        assertTrue(aircraft_carrier.okToPlaceShipAt(0,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAt() {
        // Attempting to place a ship in exactly the same place
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtOverlappingHorizontal() {
        // Attempting to place a ship which overlaps another ship / horizontally
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 3, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0, 0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtOverlappingVertical() {
        // Attempting to place a ship which overlaps another ship / vertically
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(3, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0, 0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtOutOfBoundsHorizontal() {
        // Attempting to place a ship that extends out of bounds of the grid
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,6, true, ocean));
    }
    @Test
    public void testNotOkToPlaceShipAtOutOfBoundsVertical() {
        // Attempting to place a ship that extends out of bounds of the grid
        assertFalse(aircraft_carrier.okToPlaceShipAt(6,0, false, ocean));
    }

    // tests for placing ship adjacent to a horizontal ship
    @Test
    public void testNotOkToPlaceShipAtAdjacentAboveHorizontal() {
        // Attempting to place a ship adjacent to another ship - Above/horizontal
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(1, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBelowHorizontal() {
        // Attempting to place a ship adjacent to another ship - below/horizontal
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentRightHorizontal() {
        // Attempting to place a ship adjacent to another ship - right/horizontal
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,5, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentLeftHorizontal() {
        // Attempting to place a ship adjacent to another ship - left/horizontal
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 5, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBottomLeftDiagonalHorizontal() {
        // Attempting to place a ship adjacent to another ship - bottom left diagonal/horizontal
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(0, 5, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentTopLeftDiagonalHorizontal() {
        // Attempting to place a ship adjacent to another ship - top left diagonal/horizontal
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(1, 5, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBottomRightDiagonalHorizontal() {
        // Attempting to place a ship adjacent to another ship - bottom right diagonal/horizontal
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(0, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1,1, true, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentTopRightDiagonalHorizontal() {
        // Attempting to place a ship adjacent to another ship - top right diagonal/horizontal
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(1, 0, true, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,1, true, ocean));
    }




    // tests for placing ship adjacent to a vertical ship
    @Test
    public void testNotOkToPlaceShipAtAdjacentAboveVertical() {
        // Attempting to place a ship adjacent to another ship - Above/vertical
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(5, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0, 0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBelowVertical() {
        // Attempting to place a ship adjacent to another ship - below/vertical
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(5, 0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentRightVertical() {
        // Attempting to place a ship adjacent to another ship - right/vertical
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0, 1, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentLeftVertical() {
        // Attempting to place a ship adjacent to another ship - left/vertical
        Ship testShip = new AircraftCarrier();
        testShip.placeShipAt(0, 1, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBottomLeftDiagonalVertical() {
        // Attempting to place a ship adjacent to another ship - bottom left diagonal/vertical
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(0, 1, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1, 0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentTopLeftDiagonalVertical() {
        // Attempting to place a ship adjacent to another ship - top left diagonal/vertical
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(5, 1, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(0,0, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentBottomRightDiagonalVertical() {
        // Attempting to place a ship adjacent to another ship - bottom right diagonal/vertical
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(0, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1,1, false, ocean));
    }

    @Test
    public void testNotOkToPlaceShipAtAdjacentTopRightDiagonalVertical() {
        // Attempting to place a ship adjacent to another ship - top right diagonal/vertical
        Ship testShip = new PatrolBoat();
        testShip.placeShipAt(5, 0, false, ocean);
        assertFalse(aircraft_carrier.okToPlaceShipAt(1,0, false, ocean));
    }




}
