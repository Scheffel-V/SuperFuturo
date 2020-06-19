package com.ufrgs.superfuturo.repository;

import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.RealObject;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RealObjectRepository extends JpaRepository<RealObject, Long>{

	public RealObject findByName(String name);
}
