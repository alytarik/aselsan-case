package com.alitarik.aselsanbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(String id) {
        return itemRepository.findItemById(id);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(String id, Item item) {
        Item itemToUpdate = itemRepository.findItemById(id);
        itemToUpdate.setName(item.getName());
        itemToUpdate.setStock(item.getStock());
        itemToUpdate.setPrice(item.getPrice());
        return itemRepository.save(itemToUpdate);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }
}
