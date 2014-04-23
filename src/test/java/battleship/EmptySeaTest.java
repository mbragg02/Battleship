package battleship;

import battleship.models.EmptySea;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EmptySeaTest {
	
	private EmptySea sea;

	@Before
	public void setUp() throws Exception {
		this.sea = new EmptySea();
	}

	@Test
	public void testGetLength() {
		assertEquals(1, sea.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Empty Sea", sea.getShipType());
	}

	@Test
	public void testShootAt() {
		int grid_size = 10;
		for(int row = 0; row < grid_size; row++ ) {
			for(int column = 0; column < grid_size; column++ ) {
				assertFalse(sea.shootAt(row, column));
			}
		}
	}

	@Test
	public void testIsSunk() {
		assertFalse(sea.isSunk());
	}

	@Test
	public void testToStringEmpty() {
		assertEquals("-", sea.toString());
	}

}
