package com.app.apipago.entity;

public class Respuesta {

	private String codigo;
	private String mensaje;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", mensaje=" + mensaje + "]";
	}

	public Respuesta() {
		super();
	}

	public Respuesta(String codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

}