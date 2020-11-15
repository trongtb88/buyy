package com.dragon88.microservices.inventory.controller;

import com.dragon88.microservices.inventory.dto.InventoryDetailDTO;
import com.dragon88.microservices.inventory.models.Inventory;
import com.dragon88.microservices.inventory.repository.InventoryRepository;
import com.dragon88.microservices.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dragon88
 */
@RestController
public class InventoryController {
    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryService inventoryService;


    @RequestMapping(value = "/inventory", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, produces = "application/json")
    public Inventory getInventoryById(@PathVariable("id") String id) {
        return inventoryRepository.findById(Long.parseLong(id)).get();
    }

    @RequestMapping(value = "/inventory/new/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * localhost:3333/inventory/check
     * [{
     * "inventoryId":1,
     * "quantity":9
     * },
     * {
     * "inventoryId":2,
     * "quantity":200
     * }]
     *
     * @param inventoryDetailDTOList
     * @return
     */
    @RequestMapping(value = "/inventory/check", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public List<InventoryDetailDTO> checkInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
        return inventoryService.checkForInventory(inventoryDetailDTOList);
    }

    /**
     * localhost:3333/inventory/update
     * [{
     * "inventoryId":1,
     * "quantity":10
     * },
     * {
     * "inventoryId":2,
     * "quantity":10
     * },
     * {
     * "inventoryId":3,
     * "quantity":10
     * }
     * ]
     *
     * @param inventoryDetailDTOList
     * @return
     */

    @RequestMapping(value = "/inventory/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public List<InventoryDetailDTO> updateInventory(@RequestBody List<InventoryDetailDTO> inventoryDetailDTOList) {
        return inventoryService.updateInventory(inventoryDetailDTOList);
    }


}
