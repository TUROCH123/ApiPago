package com.app.apipago.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.modelo.Pago;
import com.app.apipago.service.PagoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/pago")
@CrossOrigin(origins = "http://localhost:8095")
public class PagoControlador {

	@Autowired
	private PagoService service;
	
	@PostMapping("/realizarPago")
	public ResponseEntity<?> realizarPago(@RequestBody Pago pago) throws JsonProcessingException {
        return service.realizarPago(pago);
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/obtenerMayorIndice")
	public ResponseEntity<Respuesta> mayorIndicePago(Respuesta respuesta) {
        return (ResponseEntity<Respuesta>) service.mayorIndicePago(respuesta);
	}

}