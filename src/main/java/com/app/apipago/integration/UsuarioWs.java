package com.app.apipago.integration;

import org.springframework.http.ResponseEntity;

import com.app.apipago.excepciones.WSException;
import com.app.apipago.modelo.Usuario;

public interface UsuarioWs {
	public Usuario obtenerUsuarioPorId(Long id) throws WSException;

	public ResponseEntity<?> actualizarUsuarioPorID(Long id,Usuario usuario) throws WSException;

	public Usuario validarDatos(String email, String pass) throws WSException;
}
