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
public class PreviousAllowedFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Info info1 = new Info("sessionId","userId","addr","pagina1","pagina2","tokenSource1","tokenTarget1");
		Info info2 = new Info("sessionId","userId","addr","pagina2","pagina3","tokenSource2","tokenTarget2");
		Info info3 = new Info("sessionId","userId","addr","pagina3","logout","tokenSource2","tokenTarget2");
		try {
			new PreviousAllowedFilter().filter(info1,info2);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}		
		try {
			new PreviousAllowedFilter().filter(info2,info3);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		try {
			new PreviousAllowedFilter().filter(info3,info1);
		} catch (FilterException e) {
			assertEquals("Previous diferente", e.getMessage());
		}
	}

}
