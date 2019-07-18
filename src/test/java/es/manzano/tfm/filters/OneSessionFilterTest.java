package es.manzano.tfm.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.manzano.tfm.Info;
import es.manzano.tfm.InfoDAO;
import es.manzano.tfm.exceptions.FilterException;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneSessionFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_soloUnorigen() {
		InfoDAO infoDAO = new InfoDAO();
		Info info1 = new Info("sessionId1","userId1","addr","source1","target1","tokenSource1","tokenTarget1");
		infoDAO.set(info1);
		Info info2 = new Info("sessionId1","userId1","addr","source2","target2","tokenSource2","tokenTarget2");
		try {
			new OneSessionFilter(infoDAO).filter(info2,info1);
		} catch (FilterException e) {
			fail();
		}	
	}

	@Test
	public void test_VariosOrignenesUnaSessionrigen() {
		InfoDAO infoDAO = new InfoDAO();
		Info info1 = new Info("sessionId1","userId1","addr","source1","target1","tokenSource1","tokenTarget1");
		infoDAO.set(info1);
		Info info2 = new Info("sessionId1","userId1","addr2","source2","target2","tokenSource2","tokenTarget2");
		try {
			new OneSessionFilter(infoDAO).filter(info2,info1);
		} catch (FilterException e) {
			assertEquals("SessionId diferentes", e.getMessage());
		}	
	}

}
