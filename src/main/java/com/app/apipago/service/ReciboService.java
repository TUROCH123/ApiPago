package com.app.apipago.service;

import org.springframework.http.ResponseEntity;

//import com.app.apipago.entity.Respuesta;
//import com.app.apipago.modelo.Pago;
import com.app.apipago.modelo.Recibo;
import com.app.apipago.modelo.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface ReciboService {

	public ResponseEntity<?> realizarRecibo(Recibo recibo) throws JsonProcessingException;

//	public ResponseEntity<?> mayorIndicePago(Respuesta respuesta);

//	public ResponseEntity<?> mayorIndicePlanes(Respuesta respuesta);
	public Recibo findByUserId(String userId) throws JsonProcessingException;
}
