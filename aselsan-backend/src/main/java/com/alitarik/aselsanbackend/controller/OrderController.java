package com.alitarik.aselsanbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.service.ItemService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/")
    public ResponseEntity<?> createItem(@RequestBody List<Item> items) {
        // check stock for every item in items and then create order
        for (Item item : items) {
            if (!itemService.checkStock(item)) {
                return ResponseEntity.badRequest().body("Item " + item.getName() + " is out of stock");
            }
        }
        for (Item item : items) {
            Item itemInDb = itemService.getItemById(item.getId());
            itemInDb.setStock(itemInDb.getStock() - item.getStock());
            itemService.updateItem(item.getId(), itemInDb);
        }
        return ResponseEntity.ok().build();
    }
}
