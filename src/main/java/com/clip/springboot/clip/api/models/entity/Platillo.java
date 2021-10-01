package com.clip.springboot.clip.api.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.clip.springboot.clip.api.enums.TipoCocinaEnum;

@Entity
@Table(name = "platillo")
public class Platillo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false)
	private String nombre;

	@NotEmpty
	@Column(nullable = false)
	private String descripcion;
	
	@NotNull
	@Column(nullable = false)
	private Double precio;

	@NotNull
	@Column(nullable = false)
	private Boolean estatus;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@NotNull
	@Column(nullable = false)
	private TipoCocinaEnum tipoCocina;
	
	private Integer cantidad;

	@PrePersist
	public void prePresist() {
		createAt = new Date();
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public TipoCocinaEnum getTipoCocina() {
		return tipoCocina;
	}

	public void setTipoCocina(TipoCocinaEnum tipoCocina) {
		this.tipoCocina = tipoCocina;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}
