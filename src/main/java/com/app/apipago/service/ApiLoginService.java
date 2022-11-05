package com.app.apipago.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.apipago.entity.Respuesta;
import com.app.apipago.entity.UsuarioResponse;
import com.app.apipago.modelo.Usuario;
//import com.app.apilogin.entity.Respuesta;
//import com.app.apilogin.entity.UsuarioResponse;
//import com.app.apilogin.modelo.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface ApiLoginService {

	public ResponseEntity<UsuarioResponse> validarDatos(String email, String pass) throws JsonProcessingException;

	public Usuario findByEmail(String email) throws JsonProcessingException;

	public ResponseEntity<?> actualizarUsuarioPorID(Long id,Usuario usuarioAct) throws JsonProcessingException;

	public ResponseEntity<?> guardarUsuario(Usuario usuario) throws JsonProcessingException;

	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Long id) throws JsonProcessingException;

	public ResponseEntity<Respuesta> mayorIndiceUsuario(Respuesta respuesta);
}
