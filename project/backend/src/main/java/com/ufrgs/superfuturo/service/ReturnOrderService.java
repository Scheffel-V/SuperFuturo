package com.ufrgs.superfuturo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrgs.superfuturo.model.ReturnOrder;
import com.ufrgs.superfuturo.repository.ReturnOrderRepository;

@Service
@Transactional
public class ReturnOrderService {

	@Autowired
	private ReturnOrderRepository returnOrderRepository;
	
	public void addReturnOrder(final ReturnOrder returnOrder) {
		this.returnOrderRepository.save(returnOrder);
	}

	public List<ReturnOrder> getAllReturnOrders() {
		final List<ReturnOrder> returnOrders = new ArrayList<ReturnOrder>();
		
		this.returnOrderRepository.findAll().forEach(returnOrders::add);
		
		return returnOrders;
	}
	
	public void addReturnOrders(final List<ReturnOrder> returnOrders) {
		this.returnOrderRepository.saveAll(returnOrders);
	}
	
	public void removeAllReturnOrders() {
		this.returnOrderRepository.deleteAll();
	}
}
