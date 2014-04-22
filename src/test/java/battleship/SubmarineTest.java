package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubmarineTest {

	private Submarine submarine;

	@Before
	public void setUp() throws Exception {
		this.submarine = new Submarine();
	}

	@Test
	public void testGetLength() {
		assertEquals(3, submarine.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Submarine", submarine.getShipType());
	}
}
