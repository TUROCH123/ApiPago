package com.app.apipago.modelo;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recibos")
public class Recibo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "recibos_id")
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "suscrito")
	private String suscrito;
	
	@Column(name = "monto")
	private Double monto;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_pago", updatable = false, nullable = false)
	private Date fechaPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medioPagos_id")
	private MedioPago medioPago;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planes_id")
	private Planes planes;

	@JoinColumn(name = "userId")
	private Long userId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inscripcion", updatable = false, nullable = false)
	private Date fechaInscripcion;
	
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public Recibo() {
		super();
	}

	public Recibo(Long id, Double monto, Date fechaPago, MedioPago medioPago, Planes planes, Long userId) {
		super();
		this.id = id;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.medioPago = medioPago;
		this.planes = planes;
		this.userId = userId;
	}

	public Recibo(Double monto, Date fechaPago, MedioPago medioPago, Planes planes, Long userId, Date fechaInscripcion) {
		super();
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.medioPago = medioPago;
		this.planes = planes;
		this.userId = userId;
		this.fechaInscripcion = fechaInscripcion;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Planes getPlanes() {
		return planes;
	}

	public void setPlanes(Planes planes) {
		this.planes = planes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getSuscrito() {
		return suscrito;
	}

	public void setSuscrito(String suscrito) {
		this.suscrito = suscrito;
	}

}