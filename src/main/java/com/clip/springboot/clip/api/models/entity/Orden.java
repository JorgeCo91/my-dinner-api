package com.clip.springboot.clip.api.models.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "orden")
public class Orden implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "fecha_pedido")
	@Temporal(TemporalType.DATE)
	private Date fechaPedido;

	@Column(nullable = false)
	private String domicilioCliente;

	private Double montoTotal;

	private Double subTotal;

	@NotNull
	private Time horaPedido;

	@ManyToOne
	@JoinColumn(name = "id_cliente", referencedColumnName = "id")
	@NotNull
	private Cliente cliente;

	@JoinTable(name = "orden_platillo", joinColumns = @JoinColumn(name = "id_orden", nullable = false), inverseJoinColumns = @JoinColumn(name = "id_platillo", nullable = false))
	@ManyToMany()
	private List<Platillo> platillos;

	@PrePersist
	public void prePresist() {
		fechaPedido = new Date();
	}

	public List<Platillo> getPlatillos() {
		return platillos;
	}

	public void setPlatillos(List<Platillo> platillos) {
		this.platillos = platillos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public String getDomicilioCliente() {
		return domicilioCliente;
	}

	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Time getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Time horaPedido) {
		this.horaPedido = horaPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}

}
