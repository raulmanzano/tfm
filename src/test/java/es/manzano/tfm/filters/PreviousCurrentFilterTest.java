package es.manzano.tfm.filters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;

public class PreviousCurrentFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTrue() {
		Info infoPrevious = new Info("sessionId","userId","source","target","tokenSource","tokenTarget");
		Info infoCurrent = new Info("sessionId","userId","target","target2","tokenSource","tokenTarget");
		
		try {
			new PreviousCurrentFilter().filter(infoPrevious,infoCurrent);
		} catch (FilterException e) {
			fail();
		}	
	}

	@Test
	public void testFalse() {
		Info infoPrevious = new Info("sessionId","userId","source","targetOther","tokenSource","tokenTarget");
		Info infoCurrent = new Info("sessionId","userId","target","target2","tokenSource","tokenTarget");
		
		try {
			new PreviousCurrentFilter().filter(infoPrevious,infoCurrent);
		} catch (FilterException e) {
			assertEquals("Previous diferente", e.getMessage());
		}	
	}
	
}
