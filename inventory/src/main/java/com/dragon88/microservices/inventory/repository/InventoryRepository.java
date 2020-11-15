package com.dragon88.microservices.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.dragon88.microservices.inventory.models.Inventory;

public interface InventoryRepository  extends CrudRepository<Inventory, Long> {
	   
}



