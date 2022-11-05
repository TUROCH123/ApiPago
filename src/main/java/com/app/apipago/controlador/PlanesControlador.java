package com.app.apipago.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.apipago.entity.Respuesta;
import com.app.apipago.service.PagoService;

@RestController
@RequestMapping("/api/v1/planes")
@CrossOrigin(origins = "http://localhost:8095")
public class PlanesControlador {

	@Autowired
	private PagoService service;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/obtenerMayorIndice")
	public ResponseEntity<Respuesta> mayorIndicePlanes(Respuesta respuesta) {
        return (ResponseEntity<Respuesta>) service.mayorIndicePlanes(respuesta);
	}
}