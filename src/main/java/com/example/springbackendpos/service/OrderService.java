package com.example.springbackendpos.service;

import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.dto.impl.OrderDto;

public interface OrderService {

    void saveOrder(OrderDto orderDto);
}
