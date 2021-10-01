package com.clip.springboot.clip.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clip.springboot.clip.api.models.entity.Platillo;
import com.clip.springboot.clip.api.models.services.IPlatilloService;

@RestController
@RequestMapping("/api")
public class PlatilloRestController {
	
	@Autowired
	private IPlatilloService platilloService;
	
	@SuppressWarnings("unchecked")
	@PostMapping("/platillos")
	public ResponseEntity<?> create(@Valid @RequestBody Platillo platillo, BindingResult result) {
		
		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> responseService = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			responseService = (ResponseEntity<Map<String, Object>>) platilloService.save(platillo);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseService;
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("/platillos")
	public ResponseEntity<?> update(@Valid @RequestBody Platillo platillo, BindingResult result) {

		Map<String, Object> response = new HashMap<>();
		ResponseEntity<Map<String, Object>> responseService = null;

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			response.put("success", Boolean.FALSE);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			responseService = (ResponseEntity<Map<String, Object>>) platilloService.update(platillo);

		} catch (DataAccessException e) {
			response.put("message", "Error al realizar al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseService;
	}

}
