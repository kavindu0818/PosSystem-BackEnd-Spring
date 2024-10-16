package com.example.springbackendpos.customStatusCodes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SelectedCustomerAndItemErrorStatus {
    private int statusCode;
    private String statusMessage;
}
