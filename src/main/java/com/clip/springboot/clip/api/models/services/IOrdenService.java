package com.clip.springboot.clip.api.models.services;

import org.springframework.http.ResponseEntity;

import com.clip.springboot.clip.api.vo.OrdenVO;

public interface IOrdenService {
	
	
	public ResponseEntity<?> solicitarOrden(OrdenVO orden);

}
