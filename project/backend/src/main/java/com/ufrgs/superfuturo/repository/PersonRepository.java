package com.ufrgs.superfuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{}
