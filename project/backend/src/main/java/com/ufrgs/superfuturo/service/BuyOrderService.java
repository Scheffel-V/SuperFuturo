package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.BuyOrder;
import com.ufrgs.superfuturo.repository.BuyOrderRepository;

@Service
@Transactional
public class BuyOrderService {

	@Autowired
	private BuyOrderRepository buyOrderRepository;
	
	public void addBuyOrder(final BuyOrder buyOrder) {
		this.buyOrderRepository.save(buyOrder);
	}

	public List<BuyOrder> getAllBuyOrders() {
		final List<BuyOrder> buyOrders = new ArrayList<BuyOrder>();
		
		this.buyOrderRepository.findAll().forEach(buyOrders::add);
		
		return buyOrders;
	}
	
	public void addBuyOrders(final List<BuyOrder> buyOrders) {
		this.buyOrderRepository.saveAll(buyOrders);
	}
	
	public void removeAllBuyOrders() {
		this.buyOrderRepository.deleteAll();
	}
}
