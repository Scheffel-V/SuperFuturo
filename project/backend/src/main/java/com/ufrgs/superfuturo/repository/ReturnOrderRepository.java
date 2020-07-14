package com.ufrgs.superfuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.ReturnOrder;


@Repository
public interface ReturnOrderRepository extends JpaRepository<ReturnOrder, Long>{}
