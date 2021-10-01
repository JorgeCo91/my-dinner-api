package com.clip.springboot.clip.api.models.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.springboot.clip.api.enums.TipoCocinaEnum;
import com.clip.springboot.clip.api.models.dao.IPlatilloDao;
import com.clip.springboot.clip.api.models.entity.Platillo;

@Service
public class PlatilloServiceImpl implements IPlatilloService {
	
	@Autowired
	private IPlatilloDao platilloDao;

	@Override
	@Transactional
	public ResponseEntity<?> save(Platillo platillo) {
		Platillo platilloNew = null;
		Map<String, Object> response = new HashMap<>();
		
		platillo.setTipoCocina(TipoCocinaEnum.findById(platillo.getTipoCocina().getId()));
		platilloNew = platilloDao.save(platillo);
		
		response.put("success", Boolean.TRUE);
		response.put("data", platilloNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<?> update(Platillo platillo) {
		Platillo platilloUpdated = null;
		Map<String, Object> response = new HashMap<>();
		
		Platillo platilloActual = platilloDao.findById(platillo.getId()).orElse(null);
		
		if (platilloActual != null) {

			platilloActual.setNombre(platillo.getNombre());
			platilloActual.setDescripcion(platillo.getDescripcion());
			platilloActual.setPrecio(platillo.getPrecio());
			platilloActual.setEstatus(platillo.getEstatus());
			platilloActual.setTipoCocina(TipoCocinaEnum.findById(platillo.getTipoCocina().getId()));

			platilloUpdated = platilloDao.save(platilloActual);

		} else {
			response.put("success", Boolean.TRUE);
			response.put("message",
					"El cliente ID: ".concat(platillo.getId().toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("success", Boolean.TRUE);
		response.put("data", platilloUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
