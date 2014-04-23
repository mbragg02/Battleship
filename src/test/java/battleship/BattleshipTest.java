package battleship;

import battleship.models.Battleship;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BattleshipTest {
	
	private Battleship battleship;

	@Before
	public void setUp() throws Exception {
		this.battleship = new Battleship();
	}

	@Test
	public void testGetLength() {
		assertEquals(4, battleship.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Battleship", battleship.getShipType());
	}

}
