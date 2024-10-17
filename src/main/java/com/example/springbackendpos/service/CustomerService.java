package com.example.springbackendpos.service;


import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.entity.impl.CustomerEntity;
import com.example.springbackendpos.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    void updateCustomer(String id, CustomerDto customerDto);

    void deleteCustomer(String id) throws CustomerNotFoundException;


    List<CustomerDto> getAll();

    CustomerStatus getUser(String userId);
}
