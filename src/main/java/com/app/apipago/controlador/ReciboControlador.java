package com.app.apipago.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import com.app.apipago.entity.Respuesta;
//import com.app.apipago.modelo.Pago;
import com.app.apipago.modelo.Recibo;
import com.app.apipago.modelo.Usuario;
import com.app.apipago.repositorio.ReciboRepositorio;
//import com.app.apipago.service.PagoService;
import com.app.apipago.service.ReciboService;
import com.app.apipago.service.imp.PagoServiceImpl;
import com.app.apipago.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/recibo")
@CrossOrigin(origins = "http://localhost:8095")
public class ReciboControlador {
	private static final Logger logger = LoggerFactory.getLogger(ReciboControlador.class);
	@Autowired
	private ReciboService service;
	@Autowired
	private ReciboRepositorio reciboRepositorio;
	
	@GetMapping("/listarRecibos")
	public List<Recibo> listarRecibos() {
		return reciboRepositorio.findAll();
	}
	
	@PostMapping("/realizarRecibo")
	public ResponseEntity<?> realizarRecibo(@RequestBody Recibo recibo) throws JsonProcessingException {
		return service.realizarRecibo(recibo);
	}

	@GetMapping("/findByUserId/{userId}")
	public Recibo findByEmail(@PathVariable(value = "userId", required = true) String userId)
			throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2, "[validarDatos][userId] ", userId);
		return service.findByUserId(userId);
	}
}