package battleship;

import battleship.Destroyer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DestroyerTest {
	
	private Destroyer destroyer;

	@Before
	public void setUp() throws Exception {
		this.destroyer = new Destroyer();
	}

	@Test
	public void testGetLength() {
		assertEquals(2, destroyer.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Destroyer", destroyer.getShipType());
	}

}
