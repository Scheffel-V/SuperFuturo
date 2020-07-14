package com.ufrgs.superfuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.InputPerson;


@Repository
public interface InputPersonRepository extends JpaRepository<InputPerson, Long>{}
