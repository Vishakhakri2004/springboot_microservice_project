package com.order.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.service.OrderServiceApplication;
import com.order.service.request.OrderRequest;
import com.order.service.services.OrderServices;

@RestController

public class OrderController {
	@Autowired
	OrderServices orderServices;

	@PostMapping("/placeOrder")
	public ResponseEntity placeOrder(@RequestBody OrderRequest orderRequest) {
		orderServices.placeOrder(orderRequest);
		return ResponseEntity.ok(HttpStatus.OK);

	}
}
