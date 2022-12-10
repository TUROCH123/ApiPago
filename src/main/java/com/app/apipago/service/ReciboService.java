package com.app.apipago.service;

import org.springframework.http.ResponseEntity;
import com.app.apipago.modelo.Recibo;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface ReciboService {

	public ResponseEntity<?> realizarRecibo(Recibo recibo) throws JsonProcessingException;

	public Recibo findByUserId(String userId) throws JsonProcessingException;
}
