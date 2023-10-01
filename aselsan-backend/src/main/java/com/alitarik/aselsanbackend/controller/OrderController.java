package com.alitarik.aselsanbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.model.ItemList;
import com.alitarik.aselsanbackend.service.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Orders", description = "The Order API")
@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Create an order with given list of items")
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<?> createOrder(@RequestBody ItemList items) {
        for (Item item : items.getItems()) {
            if (!itemService.checkStock(item)) {
                return ResponseEntity.badRequest().body("Item " + item.getName() + " is out of stock");
            }
        }
        for (Item item : items.getItems()) {
            Item itemInDb = itemService.getItemById(item.getId());
            itemInDb.setStock(itemInDb.getStock() - item.getStock());
            itemService.updateItem(itemInDb);
        }
        return ResponseEntity.ok().build();
    }
}
