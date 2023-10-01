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

    public Item updateItem(Item item) {
        Item itemToUpdate = itemRepository.findItemById(item.getId());
        itemToUpdate.setName(item.getName());
        itemToUpdate.setStock(item.getStock());
        itemToUpdate.setPrice(item.getPrice());
        return itemRepository.save(itemToUpdate);
    }

    public void deleteItem(String id) {
        itemRepository.deleteById(id);
    }

    public Boolean checkStock(Item item) {
        Item itemInDb = itemRepository.findItemById(item.getId());
        if (itemInDb.getStock() >= item.getStock()) {
            return true;
        }
        return false;
    }

    public void resetItems() {
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            item.setStock(0);
            item.setPrice(item.getDefaultPrice());
            itemRepository.save(item);
        }
    }
}
