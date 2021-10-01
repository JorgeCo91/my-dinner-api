package com.clip.springboot.clip.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clip.springboot.clip.api.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
