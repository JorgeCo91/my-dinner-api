package com.clip.springboot.clip.api.models.services;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clip.springboot.clip.api.constants.OrdenConstants;
import com.clip.springboot.clip.api.models.dao.IClienteDao;
import com.clip.springboot.clip.api.models.dao.IOrdenDao;
import com.clip.springboot.clip.api.models.dao.IPlatilloDao;
import com.clip.springboot.clip.api.models.entity.Cliente;
import com.clip.springboot.clip.api.models.entity.Orden;
import com.clip.springboot.clip.api.models.entity.Platillo;
import com.clip.springboot.clip.api.vo.OrdenVO;

@Service
public class OrdenServiceImpl implements IOrdenService{
	
	@Autowired
	private IOrdenDao ordenDao;
	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IPlatilloDao platilloDao;
	
	@SuppressWarnings("null")
	@Override
	@Transactional
	public ResponseEntity<?> solicitarOrden(OrdenVO ordenVO) {
		Orden ordenNew = null;
		List<Platillo> listaPlatillos = new ArrayList<Platillo>();
		Map<String, Object> response = new HashMap<>();
		DateFormat formatter = new SimpleDateFormat("HH:mm");
		Time horaPedido = null;
		Time horaInicio = null;
		Time horaFin = null;
		
		Cliente cliente = clienteDao.findById(ordenVO.getIdCliente()).orElse(null);
		
		if (ordenVO.getPlatillos() != null && ordenVO.getPlatillos().size() < 2) {
			response.put("success", Boolean.FALSE);
			response.put("message", "La orden debo contener al menos 2 platillos");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			horaPedido = new Time( formatter.parse(ordenVO.getHoraPedido()).getTime() );
			horaInicio = new Time( formatter.parse(OrdenConstants.HORA_INICIO_SERVICIO).getTime() );
			horaFin = new Time( formatter.parse(OrdenConstants.HORA_FIN_SERVICIO).getTime() );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if (horaPedido.before(horaInicio) || horaPedido.after(horaFin)) {
			response.put("success", Boolean.FALSE);
			response.put("message", "Lo sentimos el horario solo esta disponible de las 16:00 a las 21:00 hrs");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if ( cliente != null ) {
			Orden orden = new Orden();
			Double montoTotal = 0D;
			Double subTotal = 0D;
			for (Platillo platillo : ordenVO.getPlatillos()) {
				Platillo platilloExistente = platilloDao.findById(platillo.getId()).orElse(null);
				if( platilloExistente != null ) {
					if (platilloExistente.getPrecio() != null) {
						listaPlatillos.add(platilloExistente);
						subTotal += platilloExistente.getPrecio() * platillo.getCantidad();	
					}
				} else {
					response.put("success", Boolean.TRUE);
					response.put("message",
							"El platillo ID: ".concat(platillo.getId().toString().concat(" no existe en la base de datos")));
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
				}
			}
			
			montoTotal = subTotal;
			
			orden.setSubTotal(subTotal);
			orden.setMontoTotal(montoTotal);
			orden.setDomicilioCliente(cliente.getDomicilioPrincipal());
			orden.setHoraPedido(horaPedido);
			orden.setCliente(cliente);
			orden.setPlatillos(listaPlatillos);
			ordenNew = ordenDao.save(orden);
		} else {
			response.put("success", Boolean.TRUE);
			response.put("message",
					"El cliente ID: ".concat(ordenVO.getIdCliente().toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		response.put("success", Boolean.TRUE);
		response.put("data", ordenNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
