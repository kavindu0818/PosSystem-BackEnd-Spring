package com.example.springbackendpos.service;

import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.dto.impl.OrderDto;

import java.util.List;

public interface OrderService {

    void saveOrder(OrderDto orderDto);

    List<OrderDto> getAll();
}
