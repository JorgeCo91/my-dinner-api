package com.clip.springboot.clip.api.models.services;

import org.springframework.http.ResponseEntity;

import com.clip.springboot.clip.api.models.entity.Cliente;

public interface IClienteService {
	
	public ResponseEntity<?> save(Cliente cliente);
	
	public ResponseEntity<?> update(Cliente cliente);
		
}
