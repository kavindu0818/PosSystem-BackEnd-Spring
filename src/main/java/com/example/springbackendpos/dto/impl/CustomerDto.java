package com.example.springbackendpos.dto.impl;

import com.example.springbackendpos.dto.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto implements CustomerStatus {
    private String id;
    private String name;
    private String address;
    private String salary;

}
