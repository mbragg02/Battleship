package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatrolBoatTest {
	
	private PatrolBoat patrol_boat;

	@Before
	public void setUp() throws Exception {
		this.patrol_boat = new PatrolBoat();
	}

	@Test
	public void testGetLength() {
		assertEquals(1, patrol_boat.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Patrol Boat", patrol_boat.getShipType());
	}

}
