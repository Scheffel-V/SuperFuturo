package com.ufrgs.superfuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrgs.superfuturo.model.BuyOrder;
import com.ufrgs.superfuturo.service.BuyOrderService;


@CrossOrigin
@RestController
@RequestMapping("/api/buyorder")
public class BuyOrderController {

	@Autowired
	private BuyOrderService buyOrderService;
	
	public void addBuyOrder(final BuyOrder buyOrder) {
		buyOrderService.addBuyOrder(buyOrder);
	}

}
