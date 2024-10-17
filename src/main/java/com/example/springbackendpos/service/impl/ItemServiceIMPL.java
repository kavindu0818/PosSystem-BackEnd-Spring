package com.example.springbackendpos.service.impl;

import com.example.springbackendpos.customStatusCodes.SelectedCustomerAndItemErrorStatus;
import com.example.springbackendpos.dao.ItemDao;
import com.example.springbackendpos.dto.ItemStatus;
import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.entity.impl.ItemEntity;
import com.example.springbackendpos.exception.CustomerNotFoundException;
import com.example.springbackendpos.exception.DataPersistException;
import com.example.springbackendpos.exception.ItemNotFoundException;
import com.example.springbackendpos.service.ItemService;
import com.example.springbackendpos.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDto itemDto){
        ItemEntity itemEntity = itemDao.save(mapping.toItemEntity(itemDto));

        if (itemEntity == null) {
            throw new DataPersistException("Item not save");
        }
    }

    @Override
    public void updateItem(String id, ItemDto itemDto) {
        Optional<ItemEntity> Cust= itemDao.findById(id);
        if(Cust.isPresent()){
            Cust.get().setName(itemDto.getName());
            Cust.get().setQty(itemDto.getQty());
            Cust.get().setPrice(itemDto.getPrice());
        }
    }

    @Override
    public void deleteItem(String id) throws CustomerNotFoundException, ItemNotFoundException {
        Optional<ItemEntity> existUser=itemDao.findById(id);
        if(!existUser.isPresent()){
            throw  new ItemNotFoundException("Item does not exist");
        }else {
            itemDao.deleteById(id);
        }
    }

    @Override
    public ItemStatus getItem(String icode) {
        if(itemDao.existsById(icode)){
            ItemEntity selectctItem = itemDao.getReferenceById(icode);
            return mapping.toItemDto(selectctItem);
        }else {
            return (ItemStatus) new SelectedCustomerAndItemErrorStatus(2, "User with id " + icode + " not found");
        }
    }

    @Override
    public List<ItemDto> getAll() {
        return mapping.asItemDTOList(itemDao.findAll());
    }
}
