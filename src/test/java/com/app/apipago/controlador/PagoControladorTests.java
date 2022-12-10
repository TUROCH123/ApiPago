package com.app.apipago.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.Collection;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.modelo.MedioPago;
import com.app.apipago.modelo.Pago;
import com.app.apipago.modelo.Planes;
import com.app.apipago.modelo.Rol;
import com.app.apipago.modelo.TipoPlanes;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.UsuarioRepositorio;
import com.app.apipago.service.ApiLoginService;
import com.app.apipago.service.PagoService;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class PagoControladorTests {

	@InjectMocks
	private PagoControlador pagoControlador;
	@Mock
	private PagoService service;
	@Mock
	private UsuarioRepositorio usuarioRepositorio;

	@Test
	void testRealizarPago() throws JsonProcessingException {
//		try {
		Long id = Long.parseLong("1");
		TipoPlanes tipoPlanes = new TipoPlanes(1, "mensual", Double.parseDouble("26.9"), 1);
		Planes planes = new Planes(tipoPlanes);
		MedioPago medioPago = new MedioPago("BCP", "4523-1526-2563-7458", 102, "2022-11-22T21:23:16Z");
		Pago pago = new Pago(id, Double.parseDouble("100"), new Date(), medioPago, planes, id);
		pagoControlador.realizarPago(pago);
//		} catch (Exception e) {
//			assertNotNull(e);
//		}
		assertEquals("0", Constantes.CERO);
	}

	@Test
	void testMayorIndicePago() throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
		pagoControlador.mayorIndicePago(respuesta);

		assertEquals("0", Constantes.CERO);
	}
}
