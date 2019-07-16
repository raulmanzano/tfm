package es.manzano.tfm.filters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;

public class TokenFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Info infoPrevious = new Info("sessionId","userId","source","target","tokenPrevious","tokenCurrent");
		Info infoCurrent = new Info("sessionId","userId","target","target2","tokenCurrent","tokenPrevious2");
		
		try {
			new TokenFilter().filter(infoPrevious,infoCurrent);
		} catch (FilterException e) {
			fail();
		}
		
		try {
			new TokenFilter().filter(infoCurrent,infoPrevious);
		} catch (FilterException e) {
			assertEquals("Token diferentes", e.getMessage());
		}
		
		
		
		
	}

}
