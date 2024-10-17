package com.example.springbackendpos.service.impl;

import com.example.springbackendpos.dao.OrderDao;
import com.example.springbackendpos.dto.impl.OrderDto;
import com.example.springbackendpos.entity.impl.OrderEntity;
import com.example.springbackendpos.exception.DataPersistException;
import com.example.springbackendpos.service.OrderService;
import com.example.springbackendpos.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveOrder(OrderDto orderDto){
        OrderEntity orderEntity = orderDao.save(mapping.toOrderEntity(orderDto));

        if (orderEntity == null) {
            throw new DataPersistException("Order not save");
        }
    }

    @Override
    public List<OrderDto> getAll() {
        return mapping.asOrderDTOList(orderDao.findAll());
    }
}
