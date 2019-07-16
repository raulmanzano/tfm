package es.manzano.tfm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class InfoDAOTest {
	
	InfoDAO dao = null;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.dao = new InfoDAO();
	}

	@Test
	public void testNulo() {
		Info info1 = new Info("sessionId1","userId1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = this.dao.get("xxxxxx");
		assertNull(info2);
	}

	
	@Test
	public void testEqual() {
		Info info1 = new Info("sessionId1","userId1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = this.dao.get("sessionId1");
		assertEquals(info1,info2);
	}

	@Test
	public void testSobreEscribe() {
		Info info1 = new Info("sessionId","userId1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = new Info("sessionId","userId2","source2","target2","tokenSource2","tokenTarget2");
		this.dao.set(info2);
		Info info3 = this.dao.get("sessionId");
		assertNotEquals(info1,info3);
		assertEquals(info2,info3);
	}

	@Test
	public void testReset() {
		Info info1 = new Info("sessionId1","userId1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		this.dao.reset(info1);
		Info info2 = new Info("sessionId1",null,null,null,null,null);
		Info info3 = this.dao.get(info1);
		assertEquals(info2,info3);
	}

	
	
}
