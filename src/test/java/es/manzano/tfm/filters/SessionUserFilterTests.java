package es.manzano.tfm.filters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.manzano.tfm.Info;
import es.manzano.tfm.exceptions.FilterException;

public class SessionUserFilterTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	
	
	}

	@Test
	public void testSesionDiferente() {
		Info info1 = new Info("sessionId1","userId1","source1","target1","tokenSource1","tokenTarget1");
		Info info2 = new Info("sessionId2","userId2","source2","target2","tokenSource2","tokenTarget2");
		try {
			new SessionUserFilter().filter(info2,info1);
		} catch (FilterException e) {
			assertEquals("SessionId diferentes", e.getMessage());
		}	
	}

	@Test
	public void testUserIdDiferente() {
		Info info1 = new Info("sessionId1","userId1","source1","target1","tokenSource1","tokenTarget1");
		Info info12 = new Info("sessionId1","userId2","source2","target2","tokenSource2","tokenTarget2");
		try {
			new SessionUserFilter().filter(info12,info1);
		} catch (FilterException e) {
			assertEquals("UserId diferentes", e.getMessage());
		}	
	}

	
}
