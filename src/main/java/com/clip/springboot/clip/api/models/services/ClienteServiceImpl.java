package com.clip.springboot.clip.api.models.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.springboot.clip.api.models.dao.IClienteDao;
import com.clip.springboot.clip.api.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;


	@Override
	@Transactional
	public ResponseEntity<?> save(Cliente cliente) {
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		clienteNew = clienteDao.save(cliente);

		response.put("success", Boolean.TRUE);
		response.put("data", clienteNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<?> update(Cliente cliente) {

		Cliente clienteUpdated = null;
		Map<String, Object> response = new HashMap<>();

		Cliente clienteActual = clienteDao.findById(cliente.getId()).orElse(null);
		if (clienteActual != null) {

			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellidoPaterno(cliente.getApellidoPaterno());
			clienteActual.setApellidoMaterno(cliente.getApellidoMaterno());
			clienteActual.setDomicilioPrincipal(cliente.getDomicilioPrincipal());
			clienteActual.setEmail(cliente.getEmail());
			clienteActual.setTelefono(cliente.getTelefono());

			clienteUpdated = clienteDao.save(clienteActual);

		} else {
			response.put("success", Boolean.TRUE);
			response.put("message",
					"El cliente ID: ".concat(cliente.getId().toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("success", Boolean.TRUE);
		response.put("data", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}


}
