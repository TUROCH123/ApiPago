package com.app.apipago.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoPlanes")
public class TipoPlanes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tipoPlanes_id")
	private Integer id;

	@Column(name = "nombre", nullable = false, length = 120)
	private String nombre;

	@Column(name = "precio")
	private Double precio;

	@Column(name = "tiempo")
	private int tiempo;

	public TipoPlanes() {
		super();
	}

	public TipoPlanes(Integer id, String nombre, Double precio, int tiempo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
	}

	public TipoPlanes(String nombre, Double precio, int tiempo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.tiempo = tiempo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

}
