package es.manzano.tfm.filters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PreviousCurrentFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTrue() {
		Info infoPrevious = new Info("sessionId","userId","addr","source","target","tokenSource","tokenTarget");
		Info infoCurrent = new Info("sessionId","userId","addr","target","target2","tokenSource","tokenTarget");
		
		try {
			new PreviousCurrentFilter().filter(infoPrevious,infoCurrent);
		} catch (FilterException e) {
			fail();
		}	
	}

	@Test
	public void testFalse() {
		Info infoPrevious = new Info("sessionId","userId","addr","source","targetOther","tokenSource","tokenTarget");
		Info infoCurrent = new Info("sessionId","userId","addr","target","target2","tokenSource","tokenTarget");
		
		try {
			new PreviousCurrentFilter().filter(infoPrevious,infoCurrent);
		} catch (FilterException e) {
			assertEquals("Previous diferente", e.getMessage());
		}	
	}
	
}
