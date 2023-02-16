
package sniaper.project.MySimpleProjectParserForWork;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestDetective {
	private final double EPS = 1e-9;
	private City city;
	
	@Before
	public void createNewCity() {
		city = new City();
	}
	
	@Test(timeout = 100)
	public void whenCreatingAnObjectCoordinatesAreMissing() {
		double sumCoords = city.getXcoord() + city.getYcoord();
		Assert.assertEquals(.0, sumCoords, EPS);
	}
	
	@Test
	public void arrayComposition() {
		Assert.assertEquals("Test Not Executed", 
				city.getCoordinateParameters(), 
				new ArrayList<Double>() {{
					add(0.0);
					add(0.0);
				}});
	}
	
}
