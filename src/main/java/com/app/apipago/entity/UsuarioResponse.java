package com.app.apipago.entity;

import com.app.apipago.modelo.Usuario;

public class UsuarioResponse {
	
	private Respuesta respuesta;
	private Usuario usuario;
	
	public UsuarioResponse() {
		super();
	}

	public UsuarioResponse(Respuesta respuesta, Usuario usuario) {
		super();
		this.respuesta = respuesta;
		this.usuario = usuario;
	}

	public Respuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}