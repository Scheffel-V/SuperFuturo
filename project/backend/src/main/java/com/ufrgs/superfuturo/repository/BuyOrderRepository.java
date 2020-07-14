package com.ufrgs.superfuturo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufrgs.superfuturo.model.BuyOrder;


@Repository
public interface BuyOrderRepository extends JpaRepository<BuyOrder, Long>{}
