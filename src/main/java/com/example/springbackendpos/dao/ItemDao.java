package com.example.springbackendpos.dao;

import com.example.springbackendpos.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemEntity,String> {
}
