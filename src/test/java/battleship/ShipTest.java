package battleship;

import battleship.Ship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

public class ShipTest {
	
	private Ship ship;
	
	@Before
	public void setUp() throws Exception {
		this.ship = mock(Ship.class, CALLS_REAL_METHODS);
		this.ship.setBowRow(1);
		this.ship.setBowColumn(2);
		this.ship.setHorizontal(false);
		this.ship.length = 3; // mock ship length
		this.ship.hit = new boolean[] {false, false, false, false, false}; // mock hit array
	}

	@Test
	public void testGetBowRow() {
		assertEquals(1, this.ship.getBowRow());
	}

	@Test
	public void testGetBowColumn() {
		assertEquals(2, this.ship.getBowColumn());
	}

	@Test
	public void testIsHorizontal() {
		assertFalse(this.ship.isHorizontal());
	}

	@Test
	public void testSetBowRow() {
		this.ship.setBowRow(3);
		assertEquals(3, this.ship.getBowRow());
	}

	@Test
	public void testSetBowColumn() {
		this.ship.setBowColumn(4);
		assertEquals(4, this.ship.getBowColumn());
	}

	@Test
	public void testSetHorizontal() {
		this.ship.setHorizontal(true);
		assertTrue(this.ship.isHorizontal());
	}

	@Test
	public void testOkToPlaceShipAt() {
		// TODO
		fail("Not yet implemented");
	}

	@Test
	public void testPlaceShipAt() {
		// TODO
		fail("Not yet implemented");
	}

	@Test
	public void testShootAt() {
		
		assertTrue(this.ship.shootAt(1, 2));
		assertFalse(this.ship.shootAt(5, 6));
	}

	@Test
	public void testIsNotSunk() {
		assertFalse(this.ship.isSunk());	
	}
	@Test
	public void testIsSunk() {
		this.ship.hit[0] = true;
		this.ship.hit[1] = true;
		this.ship.hit[2] = true;
		assertTrue(this.ship.isSunk());	
	}

	@Test
	public void testToStringNotSunk() {
		assertEquals("S", this.ship.toString());
	}
	
	@Test
	public void testToStringSunk() {
		this.ship.hit[0] = true;
		this.ship.hit[1] = true;
		this.ship.hit[2] = true;
		assertEquals("x", this.ship.toString());

	}

}
