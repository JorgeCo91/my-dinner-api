package com.clip.springboot.clip.api.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.clip.springboot.clip.api.models.entity.Platillo;

public interface IPlatilloDao extends CrudRepository<Platillo, Long>{

}
