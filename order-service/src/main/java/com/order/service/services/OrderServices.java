package com.order.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.entity.OrderEntity;
import com.order.service.repositry.OrderRopsitry;
import com.order.service.request.OrderRequest;

@Service
public class OrderServices {
	@Autowired
	OrderRopsitry orderRepositry;

	public int placeOrder(OrderRequest orderRequest) {
		OrderEntity entity = new OrderEntity();
		entity.setDescription(orderRequest.getDescription());
		entity.setItemName(orderRequest.getItemName());
		entity.setOrderStatus(orderRequest.getOrderStatus());
		entity.setQty(orderRequest.getQty());
		entity = orderRepositry.save(entity);
		return entity.getId();

	}

}
