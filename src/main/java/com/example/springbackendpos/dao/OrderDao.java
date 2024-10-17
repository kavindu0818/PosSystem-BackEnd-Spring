package com.example.springbackendpos.dao;

import com.example.springbackendpos.entity.impl.ItemEntity;
import com.example.springbackendpos.entity.impl.OrderEntity;
import com.example.springbackendpos.service.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity,String> {
}
