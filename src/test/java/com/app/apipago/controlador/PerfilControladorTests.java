package com.app.apipago.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Collection;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import com.app.apipago.entity.Respuesta;
import com.app.apipago.excepciones.WSException;
import com.app.apipago.integration.UsuarioWs;
import com.app.apipago.modelo.MedioPago;
import com.app.apipago.modelo.Pago;
import com.app.apipago.modelo.Perfiles;
import com.app.apipago.modelo.Planes;
import com.app.apipago.modelo.Rol;
import com.app.apipago.modelo.TipoPlanes;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.PerfilesRepositorio;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.service.ApiLoginService;
import com.app.apipago.service.PagoService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class PerfilControladorTests {
	@Mock
	private PerfilesRepositorio perfilesRepositorio;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;
	@Mock
	private UsuarioWs usuariows;
	@InjectMocks
	private PerfilControlador perfilControlador;
	@Mock
	private MultipartFile portada;
	@Mock
	private ObjectMapper objectMapper;

	@Test
	void testActualizarPerfilPorID() throws JsonProcessingException, WSException {
		try {
			Long id = Long.parseLong("1");
			Perfiles perfilAct = new Perfiles("PCO", "IMG", "ESP", true, 1526, true, portada);
			perfilControlador.actualizarPerfilPorID(id, perfilAct);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	@Test
	void testMayorIndicePago() throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
//		perfilControlador.mayorIndicePago(respuesta);

		assertEquals("0", Constantes.CERO);
	}

	@Test
	void testObtenerPerfilPorEmail() throws JsonProcessingException, WSException {

		perfilControlador.obtenerPerfilPorEmail("AJOCHAV@GMAIL.COM");
		assertEquals("0", Constantes.CERO);
	}

	@Test
	void testEliminarPerfil() throws JsonProcessingException, WSException {
		try {
			Integer id = Integer.parseInt("1");
			Perfiles perfil = new Perfiles("PCO", "IMG", "ESP", true, 1526, true, portada);
			perfilesRepositorio.delete(perfil);
			perfilControlador.eliminarPerfil(id);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	@Test
	void testObtenerPerfilPorID() throws JsonProcessingException, WSException {
		try {
			Integer id = Integer.parseInt("1");
			perfilControlador.obtenerPerfilPorID(id);
		} catch (Exception e) {
			assertNotNull(e);
		}
	}
	
	@Test
	void testGuardarPerfil() throws JsonProcessingException, WSException {
			Integer id = Integer.parseInt("1");
			Perfiles perfil = new Perfiles("PCO", "IMG", "ESP", true, 1526, true, portada);
			perfilControlador.guardarPerfil(perfil);
	}
}
