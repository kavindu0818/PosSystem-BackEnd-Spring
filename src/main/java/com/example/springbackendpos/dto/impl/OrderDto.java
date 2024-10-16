package com.example.springbackendpos.dto.impl;

import com.example.springbackendpos.dto.OrderStatus;
import com.example.springbackendpos.entity.impl.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto implements OrderStatus {

    private String orderId;
    private Date orderDate;
    private CustomerEntity customer;
    private double total;
    private double txtCash;
    private double txtDiscount;
}
