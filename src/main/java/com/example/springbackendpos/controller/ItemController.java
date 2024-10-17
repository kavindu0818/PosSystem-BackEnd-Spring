package com.example.springbackendpos.controller;

import com.example.springbackendpos.dto.CustomerStatus;
import com.example.springbackendpos.dto.ItemStatus;
import com.example.springbackendpos.dto.impl.CustomerDto;
import com.example.springbackendpos.dto.impl.ItemDto;
import com.example.springbackendpos.exception.CustomerNotFoundException;
import com.example.springbackendpos.exception.DataPersistException;
import com.example.springbackendpos.exception.ItemNotFoundException;
import com.example.springbackendpos.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveItem(@RequestBody ItemDto itemDto) {
        try {
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemStatus getSelectedItem(@PathVariable("itemCode") String iCode) {
        return itemService.getItem(iCode);
    }

    @GetMapping
    public List<ItemDto> getAllCustomer(){
        return itemService.getAll();
    }

    @PutMapping(value = "/{code}")
    public ResponseEntity<Void> updateItem(@PathVariable("code") String itemCode, @RequestBody ItemDto itemDto) {
//        logger.info("Inside the customer update method");
        try {
            itemService.updateItem(itemCode, itemDto);
//            logger.info("customer update successful");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{code}")
    public ResponseEntity<Void> deleteItem(@PathVariable("code") String itemCode) {
//        logger.info("Inside the customer delete method");
        try {
            itemService.deleteItem(itemCode);
//            logger.info("customer delete successful");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerNotFoundException e) {
            e.printStackTrace();
//            logger.warning("Error delete customer");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ItemNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
