package com.app.apipago.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "planes")
public class Planes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planes_id")
	private Integer id;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoPlanes_id")
    private TipoPlanes tipoPlanes;

	public Planes() {
		super();
	}

	public Planes(Integer id, TipoPlanes tipoPlanes) {
		super();
		this.id = id;
		this.tipoPlanes = tipoPlanes;
	}

	public Planes(TipoPlanes tipoPlanes) {
		super();
		this.tipoPlanes = tipoPlanes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoPlanes getTipoPlanes() {
		return tipoPlanes;
	}

	public void setTipoPlanes(TipoPlanes tipoPlanes) {
		this.tipoPlanes = tipoPlanes;
	}

}
