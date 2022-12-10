package com.app.apipago.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medioPagos")
public class MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medioPagos_id")
	private Integer id;

	@Column(name = "titulo", nullable = false, length = 120)
	private String titulo;

	@Column(name = "numeroTarjeta", nullable = false, length = 120)
	private String numeroTarjeta;

	@Column(name = "ccv")
	private int ccv;

	@Column(name = "fechaVencimiento", nullable = false, length = 40)
	private String fechaVencimiento;

	public MedioPago() {
		super();
	}

	public MedioPago(Integer id, String titulo, String numeroTarjeta, int ccv, String fechaVencimiento) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.numeroTarjeta = numeroTarjeta;
		this.ccv = ccv;
		this.fechaVencimiento = fechaVencimiento;
	}

	public MedioPago(String titulo, String numeroTarjeta, int ccv, String fechaVencimiento) {
		super();
		this.titulo = titulo;
		this.numeroTarjeta = numeroTarjeta;
		this.ccv = ccv;
		this.fechaVencimiento = fechaVencimiento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public int getCcv() {
		return ccv;
	}

	public void setCcv(int ccv) {
		this.ccv = ccv;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}