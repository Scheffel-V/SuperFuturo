package com.ufrgs.superfuturo.repository;

import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.RealObjectPack;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RealObjectPackRepository extends JpaRepository<RealObjectPack, Long>{

	public RealObjectPack findByName(String name);
}
