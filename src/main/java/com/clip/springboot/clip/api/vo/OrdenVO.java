package com.clip.springboot.clip.api.vo;

import java.util.List;

import com.clip.springboot.clip.api.models.entity.Platillo;

public class OrdenVO {
	
	private Double montoTotal;
	private String horaPedido;
	private Long idCliente;
	private List<Platillo> platillos;
	
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getHoraPedido() {
		return horaPedido;
	}
	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public List<Platillo> getPlatillos() {
		return platillos;
	}
	public void setPlatillos(List<Platillo> platillos) {
		this.platillos = platillos;
	}
}
