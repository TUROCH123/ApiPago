package com.app.apipago.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "perfiles")
public class Perfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "perfiles_id")
	private Integer id;

	@Column(name = "alias", nullable = false, length = 60)
	private String alias;

	@Column(name = "avatar", nullable = false, length = 200)
	private String avatar;

	@Column(name = "idioma", nullable = false, length = 60)
	private String idioma;

	@Column(name = "reproduccionAutomatica", nullable = false)
	private boolean reproduccionAutomatica;

	@Column(name = "ping", nullable = false, length = 4)
	private int ping;

	@Column(name = "controlParental", nullable = false)
	private boolean controlParental;
	
	@Transient
	private MultipartFile portada;
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@JoinTable(name = "perfiles_miListas", joinColumns = @JoinColumn(name = "perfiles_id"), inverseJoinColumns = @JoinColumn(name = "miListas_id"))
//	private List<MiLista> miListas;

	public Perfiles() {
		super();
	}
	public Perfiles(String alias, String avatar, String idioma, boolean reproduccionAutomatica, int ping,
			boolean controlParental,MultipartFile portada) {
		super();
		this.alias = alias;
		this.avatar = avatar;
		this.idioma = idioma;
		this.reproduccionAutomatica = reproduccionAutomatica;
		this.ping = ping;
		this.controlParental = controlParental;
		this.portada = portada;
	}
	
//	public Perfiles(Integer id, String alias, String avatar, String idioma, boolean reproduccionAutomatica, int ping,
//			boolean controlParental, List<MiLista> miListas) {
//		super();
//		this.id = id;
//		this.alias = alias;
//		this.avatar = avatar;
//		this.idioma = idioma;
//		this.reproduccionAutomatica = reproduccionAutomatica;
//		this.ping = ping;
//		this.controlParental = controlParental;
//		this.miListas = miListas;
//	}

//	public List<MiLista> getMiListas() {
//		return miListas;
//	}

//	public void setMiListas(List<MiLista> miListas) {
//		this.miListas = miListas;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isReproduccionAutomatica() {
		return reproduccionAutomatica;
	}

	public void setReproduccionAutomatica(boolean reproduccionAutomatica) {
		this.reproduccionAutomatica = reproduccionAutomatica;
	}

	public int getPing() {
		return ping;
	}

	public void setPing(int ping) {
		this.ping = ping;
	}

	public boolean isControlParental() {
		return controlParental;
	}

	public void setControlParental(boolean controlParental) {
		this.controlParental = controlParental;
	}

	public MultipartFile getPortada() {
		return portada;
	}

	public void setPortada(MultipartFile portada) {
		this.portada = portada;
	}
}