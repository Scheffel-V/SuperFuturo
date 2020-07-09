package com.ufrgs.superfuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.InputObject;


@Repository
public interface InputObjectRepository extends JpaRepository<InputObject, Long>{}