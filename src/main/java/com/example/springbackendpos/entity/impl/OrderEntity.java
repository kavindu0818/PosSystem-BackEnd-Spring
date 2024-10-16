package com.example.springbackendpos.entity.impl;

import com.example.springbackendpos.entity.SuperEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class OrderEntity implements SuperEntity {

    @Id
   private String orderId;
   private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer;

   private double total;
   private double txtCash;
   private double txtDiscount;
}
