package com.example.springbackendpos.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemEntity {

    @Id
    private String code;
    private String name;
    private String qty;
    private String price;

}
