package com.example.springbackendpos.util;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.entity.impl.CustomerEntity;
import com.example.springbackendpos.entity.impl.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public CustomerEntity toCustomerEntity(CustomerDto customerDto){
        return modelMapper.map(customerDto, CustomerEntity.class);
    }

    public CustomerDto toCustomerDto(CustomerEntity customerEntity){
        return modelMapper.map(customerEntity, CustomerDto.class);
    }


    public ItemEntity toItemEntity(ItemDto itemDto){
        return modelMapper.map(itemDto, ItemEntity.class);
    }

    public ItemDto toItemDto(ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDto.class);
    }

    public List<CustomerDto> asCustomerDTOList(List<CustomerEntity> customerEntities){
        return modelMapper.map(customerEntities, new TypeToken<List<CustomerDto>>(){}.getType());
    }
}
