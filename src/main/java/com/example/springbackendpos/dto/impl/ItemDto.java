package com.example.springbackendpos.dto.impl;
import com.example.springbackendpos.dto.ItemStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDto implements ItemStatus {

    private String code;
    private String name;
    private String qty;
    private String price;
}
