package battleship;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AircraftCarrierTest {
	
	private AircraftCarrier aircraft_carrier;

	@Before
	public void setUp() throws Exception {
		this.aircraft_carrier = new AircraftCarrier();
	}

	@Test
	public void testGetLength() {
		assertEquals(5, aircraft_carrier.getLength());
	}

	@Test
	public void testGetShipType() {
		assertEquals("Aircraft Carrier", aircraft_carrier.getShipType());
	}

}
