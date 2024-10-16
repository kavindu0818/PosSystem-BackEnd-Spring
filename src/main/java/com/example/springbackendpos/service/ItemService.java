package com.example.springbackendpos.service;

import com.example.springbackendpos.dto.ItemStatus;
import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.exception.CustomerNotFoundException;
import com.example.springbackendpos.exception.ItemNotFoundException;

public interface ItemService {
    void saveItem(ItemDto itemDto);

    void updateItem(String id, ItemDto itemDto);

    void deleteItem(String id) throws CustomerNotFoundException, ItemNotFoundException;

    ItemStatus getItem(String code);
}
