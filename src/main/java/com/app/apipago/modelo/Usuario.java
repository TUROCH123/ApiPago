package com.app.apipago.modelo;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;

	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
	private Collection<Rol> roles;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "planes_id")
	private Planes planes;
	
	private String suscrito;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "usuarios_medioPagos", joinColumns = @JoinColumn(name = "usuarios_id"), inverseJoinColumns = @JoinColumn(name = "medioPagos_id"))
	private List<MedioPago> medioPago;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_Inscripcion", updatable = false, nullable = false)
	private Date fechaInscripcion;
	
	@Column(name = "fecha_Vencimiento", length = 60)
	private String fechaVencimiento;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	public Usuario(Long id, String nombre, String apellido, String email, String password, Collection<Rol> roles,
			String suscrito, Date fechaInscripcion, String fechaVencimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.suscrito = suscrito;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaVencimiento = fechaVencimiento;
	}

	public Usuario(String nombre, String apellido, String email, String password, Collection<Rol> roles,
			String suscrito, Date fechaInscripcion, String fechaVencimiento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.suscrito = suscrito;
		this.fechaInscripcion = fechaInscripcion;
		this.fechaVencimiento = fechaVencimiento;
	}

	public Usuario() {
		
	}

	public String getSuscrito() {
		return suscrito;
	}

	public void setSuscrito(String suscrito) {
		this.suscrito = suscrito;
	}

	public Planes getPlanes() {
		return planes;
	}

	public void setPlanes(Planes planes) {
		this.planes = planes;
	}

	public List<MedioPago> getMedioPago() {
		return medioPago;
	}

	public void setMedioPago(List<MedioPago> medioPago) {
		this.medioPago = medioPago;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}

	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

}