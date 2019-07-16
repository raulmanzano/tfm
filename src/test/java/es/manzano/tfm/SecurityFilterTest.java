package es.manzano.tfm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import es.manzano.tfm.exceptions.FilterException;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SecurityFilterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@After
	public void setUp() throws Exception {
		SecurityFilter.reset();
	}

	@Test
	public void testNAvegacionOrdinaria() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
		
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina2 - Peticion REST a la misma pagina
		try {
			SecurityFilter.filter(sessionId, userId, "pagina2", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina3 - pagina de formulario
		token = String.valueOf(100000 + new Random().nextFloat() * 900000);

		try {
			SecurityFilter.filter(sessionId, userId, "pagina2", "pagina3", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina4
		try {
			SecurityFilter.filter(sessionId, userId, "pagina3", "pagina4", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// logout
		try {
			SecurityFilter.filter(sessionId, userId, "pagina4", "logout", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
	}

	// intento de peticion de web privada sin validacion mediante id de session
	@Test
	public void test_robo_idsession_sin_validacion() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
		
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina2 otro usuario
		try {
			SecurityFilter.filter(sessionId, null, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(e.getMessage(), "UserId nulo");
		}
	}

	// intento de peticion de web privada validado(otro usuar mediante otro Id de
	// sesion
	@Test
	public void test_robo_idsession_con_validacion() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
		
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina2 intento de peticion de otro usuario
		try {
			SecurityFilter.filter(sessionId, "userId2", "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(e.getMessage(), "UserId diferentes");
		}

	}

	// intento de acceso a web no permitida
	@Test
	public void test_pagina_no_permitida() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
		
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina4 pagina no permitida
		try {
			SecurityFilter.filter(sessionId, userId, "pagina2", "pagina4", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(e.getMessage(), "Acceso desde pagina no permitida");
		}
	}

	// error en token en formulario intentado usar uno viejo
	@Test
	public void test_token_antiguo() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
	
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina3 - pagina de formulario
		String token2 = String.valueOf(100000 + new Random().nextFloat() * 900000);

		try {
			SecurityFilter.filter(sessionId, userId, "pagina2", "pagina3", null, token2);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina4
		try {
			SecurityFilter.filter(sessionId, userId, "pagina3", "pagina4", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(e.getMessage(), "Token diferentes");
		}

	}

	// navegacion ordinaria despues de un error de seguridad
	@Test
	public void testNAvegacionOrdinariaPostError() {

		String token = String.valueOf(100000 + new Random().nextFloat() * 900000);
		String sessionId = "sessionId";
		String userId = "userId";
	
		// login
		try {
			SecurityFilter.filter(sessionId, SecurityFilter.NO_USER, null, "login", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina1
		try {
			SecurityFilter.filter(sessionId, userId, "login", "pagina1", token, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}
		// pagina2
		try {
			SecurityFilter.filter(sessionId, userId, "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			fail();
		}

		// pagina2 intento de peticion de otro usuario
		try {
			SecurityFilter.filter(sessionId, "userId2", "pagina1", "pagina2", null, null);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(e.getMessage(), "UserId diferentes");
		}

		// pagina3 - pagina de formulario
		token = String.valueOf(100000 + new Random().nextFloat() * 900000);

		try {
			SecurityFilter.filter(sessionId, userId, "pagina2", "pagina3", null, token);
		} catch (FilterException e) {
			System.out.println(e);
			assertEquals(true, true);
		}

	}

}
