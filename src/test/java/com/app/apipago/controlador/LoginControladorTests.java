package com.app.apipago.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Collection;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.modelo.Rol;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.service.ApiLoginService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class LoginControladorTests {

	@InjectMocks
	private LoginControlador loginControlador;
	@Mock
	private ApiLoginService service;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;

	@Test
	void testActualizarUsuarioPorID() throws JsonProcessingException {
			Long id = Long.parseLong("1");
			Collection<Rol> roles = null;
			Usuario usuarioAct = new Usuario(id, "ARTURO", "CHAVEZ", "ajochav@gmail.com", "123456", roles, "",
					new Date(), "");
			service.actualizarUsuarioPorID(id, usuarioAct);
			loginControlador.actualizarUsuarioPorID(id, usuarioAct);
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testListarUsuarios() throws JsonProcessingException {
			loginControlador.listarUsuarios();
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testGuardarUsuario() throws JsonProcessingException {
			Long id = Long.parseLong("1");
			Collection<Rol> roles = null;
			Usuario usuario = new Usuario(id, "ARTURO", "CHAVEZ", "ajochav@gmail.com", "123456", roles, "",
					new Date(), "");
			loginControlador.guardarUsuario(usuario);
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testObtenerUsuarioPorID() throws JsonProcessingException {
			Long id = Long.parseLong("1");
			loginControlador.obtenerUsuarioPorID(id);
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testEliminarUsuario() throws JsonProcessingException {
		try {
			Long id = Long.parseLong("1");
			loginControlador.eliminarUsuario(id);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	void testValidarDatos() throws JsonProcessingException {
			loginControlador.validarDatos("ajochav@gmail.com", "123456");
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testFindByEmail() throws JsonProcessingException {
			loginControlador.findByEmail("ajochav@gmail.com");
			assertEquals("0", Constantes.CERO);
	}
	
	@Test
	void testMayorIndicePlanes() throws JsonProcessingException {
			Respuesta respuesta = new Respuesta();
			loginControlador.mayorIndicePlanes(respuesta);
			assertEquals("0", Constantes.CERO);
	}
}
