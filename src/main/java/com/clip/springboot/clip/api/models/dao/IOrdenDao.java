package com.clip.springboot.clip.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clip.springboot.clip.api.models.entity.Orden;

public interface IOrdenDao extends CrudRepository<Orden, Long>{

}
