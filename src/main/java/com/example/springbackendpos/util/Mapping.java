package com.example.springbackendpos.util;
import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.dto.impl.OrderDto;
import com.example.springbackendpos.entity.impl.CustomerEntity;
import com.example.springbackendpos.entity.impl.ItemEntity;
import com.example.springbackendpos.entity.impl.OrderEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

    public CustomerDto toCustomerDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }


    public ItemEntity toItemEntity(ItemDto itemDto) {
        return modelMapper.map(itemDto, ItemEntity.class);
    }

    public ItemDto toItemDto(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    public OrderEntity toOrderEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, OrderEntity.class);
    }

    public OrderDto toOrderDto(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, OrderDto.class);
    }

    public List<CustomerDto> asCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDto>>(){}.getType());
    }

    public List<ItemDto> asItemDTOList(List<ItemEntity> itemEntities){
        return modelMapper.map(itemEntities, new TypeToken<List<ItemDto>>(){}.getType());
    }

    public List<OrderDto> asOrderDTOList(List<OrderEntity> orderEntities){
        return modelMapper.map(orderEntities, new TypeToken<List<OrderDto>>(){}.getType());
    }
}

