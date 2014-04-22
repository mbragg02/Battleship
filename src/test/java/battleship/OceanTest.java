package battleship;

import battleship.Ocean;
import battleship.Ship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OceanTest {
	
	private Ocean ocean;

	@Before
	public void setUp() throws Exception {
		this.ocean = new Ocean();
		
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsOccupied() {
		fail("Not yet implemented");
	}

	@Test
	public void testShootAt() {
		fail("Not yet implemented");
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
	public void testToString() {
		fail("Not yet implemented");
	}

}
