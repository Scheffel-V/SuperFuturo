package com.ufrgs.superfuturo.repository;

import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.InputObjectPack;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InputObjectPackRepository extends JpaRepository<InputObjectPack, Long>{

	public InputObjectPack findByName(String name);
}
