package com.ufrgs.superfuturo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrgs.superfuturo.model.ReturnOrder;
import com.ufrgs.superfuturo.service.ReturnOrderService;


@CrossOrigin
@RestController
@RequestMapping("/api/returnorder")
public class ReturnOrderController {
	
	@Autowired
	private ReturnOrderService returnOrderService;

	public void addReturnOrder(final ReturnOrder returnOrder) {
		returnOrderService.addReturnOrder(returnOrder);
	}
}
