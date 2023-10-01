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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/items")
@Tag(name = "Items", description = "The Item API")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get all items")
    @GetMapping("/")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @Operation(summary = "Get an item by id")
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @Operation(summary = "Create an item")
    @PostMapping(value = "/", consumes = "application/json")
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @Operation(summary = "Update an item")
    @PutMapping(value = "/{id}", consumes = "application/json")
    public Item updateItem(@PathVariable String id, @RequestBody Item item) {
        return itemService.updateItem(item);
    }

    @Operation(summary = "Delete an item")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable String id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

}
