package com.order.service.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.service.entity.OrderEntity;

@Repository
public interface OrderRopsitry extends JpaRepository<OrderEntity, Integer> {

}
