package es.manzano.tfm;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void nulo() {
		Info info1 = new Info("sessionId1","userId1","addr1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = this.dao.get("xxxxxx");
		assertNull(info2);
	}

	
	@Test
	public void equal() {
		Info info1 = new Info("sessionId1","userId1","addr1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = this.dao.get("sessionId1");
		assertEquals(info1,info2);
	}

	@Test
	public void sobreEscribe() {
		Info info1 = new Info("sessionId","userId1","addr1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		Info info2 = new Info("sessionId","userId2","addr1","source2","target2","tokenSource2","tokenTarget2");
		this.dao.set(info2);
		Info info3 = this.dao.get("sessionId");
		assertNotEquals(info1,info3);
		assertEquals(info2,info3);
	}

	@Test
	public void reset() {
		Info info1 = new Info("sessionId1","userId1","addr1","source1","target1","tokenSource1","tokenTarget1");
		this.dao.set(info1);
		this.dao.reset(info1);
		Info info2 = new Info("sessionId1",null,null,null,null,null,null);
		Info info3 = this.dao.get(info1);
		assertEquals(info2,info3);
	}

	
	
}
