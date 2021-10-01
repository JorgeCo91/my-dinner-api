package com.clip.springboot.clip.api.models.services;

import org.springframework.http.ResponseEntity;

import com.clip.springboot.clip.api.models.entity.Platillo;

public interface IPlatilloService {
	
	public ResponseEntity<?> save(Platillo platillo);
	
	public ResponseEntity<?> update(Platillo platillo);

}
