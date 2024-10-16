package com.example.springbackendpos.service;


import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.exception.CustomerNotFoundException;

public interface CustomerService {
    void saveCustomer(CustomerDto customerDto);

    void updateCustomer(String id, CustomerDto customerDto);

    void deleteCustomer(String id) throws CustomerNotFoundException;

    CustomerStatus getUser(String userId);
}
