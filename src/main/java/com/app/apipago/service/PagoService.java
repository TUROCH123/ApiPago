package com.app.apipago.service;

import org.springframework.http.ResponseEntity;

import com.app.apipago.entity.Respuesta;
import com.app.apipago.modelo.Pago;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface PagoService {

	public ResponseEntity<?> realizarPago(Pago pago)throws JsonProcessingException;

	public ResponseEntity<?> mayorIndicePago(Respuesta respuesta);
	
	public ResponseEntity<?> mayorIndicePlanes(Respuesta respuesta);
}
