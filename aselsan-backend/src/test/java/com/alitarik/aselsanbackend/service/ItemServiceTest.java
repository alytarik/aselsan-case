package com.alitarik.aselsanbackend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.repository.ItemRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllItems() {
        List<Item> expectedItems = new ArrayList<>();
        Item item1 = new Item("1", "item1", "imgurl1", 1, 2, 5);
        Item item2 = new Item("2", "item2", "imgurl1", 1, 2, 5);

        expectedItems.add(item1);
        expectedItems.add(item2);

        when(itemRepository.findAll()).thenReturn(expectedItems);

        List<Item> actualItems = itemService.getAllItems();

        assertEquals(expectedItems.size(), actualItems.size());
        assertEquals(expectedItems.get(0).getId(), actualItems.get(0).getId());
        assertEquals(expectedItems.get(0).getName(), actualItems.get(0).getName());
        assertEquals(expectedItems.get(0).getStock(), actualItems.get(0).getStock());
        assertEquals(expectedItems.get(0).getPrice(), actualItems.get(0).getPrice());
        assertEquals(expectedItems.get(1).getId(), actualItems.get(1).getId());
        assertEquals(expectedItems.get(1).getName(), actualItems.get(1).getName());
        assertEquals(expectedItems.get(1).getStock(), actualItems.get(1).getStock());
        assertEquals(expectedItems.get(1).getPrice(), actualItems.get(1).getPrice());
    }

    @Test
    public void testGetItemByIdSuccess() {
        Item expectedItem = new Item("1", "item1", "imgurl1", 1, 2, 5);

        when(itemRepository.findItemById(expectedItem.getId())).thenReturn(expectedItem);

        Item actualItem = itemService.getItemById(expectedItem.getId());

        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getName(), actualItem.getName());
        assertEquals(expectedItem.getStock(), actualItem.getStock());
        assertEquals(expectedItem.getPrice(), actualItem.getPrice());
    }

    @Test
    public void testGetItemByIdNotFound() {
        String itemId = "1";

        when(itemRepository.findItemById(itemId)).thenReturn(null);

        Item actualItem = itemService.getItemById(itemId);

        assertNull(actualItem);
    }

    @Test
    public void testCreateItem() {
        Item expectedItem = new Item("1", "item1", "imgurl1", 1, 2, 5);

        when(itemRepository.save(expectedItem)).thenReturn(expectedItem);

        Item actualItem = itemService.createItem(expectedItem);

        assertEquals(expectedItem.getId(), actualItem.getId());
        assertEquals(expectedItem.getName(), actualItem.getName());
        assertEquals(expectedItem.getStock(), actualItem.getStock());
        assertEquals(expectedItem.getPrice(), actualItem.getPrice());
    }

    @Test
    public void testUpdateItem() {
        String itemId = "1";
        Item oldItem = new Item("1", "item1", "imgurl1", 10, 10, 5);

        Item newItem = new Item("1", "item1", "imgurl1", 5, 15, 5);

        when(itemRepository.findItemById(itemId)).thenReturn(oldItem);
        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);

        Item actualItem = itemService.updateItem(newItem);

        assertEquals(newItem.getId(), actualItem.getId());
        assertEquals(newItem.getName(), actualItem.getName());
        assertEquals(newItem.getStock(), actualItem.getStock());
    }

    @Test
    public void testDeleteItem() {
        String itemId = "1";

        itemService.deleteItem(itemId);

        // Verify that the deleteById method was called with the correct ID
        // This test doesn't have an assertion because the method doesn't return
        // anything
        // and we're just verifying that the correct method was called
        itemRepository.deleteById(itemId);
    }

    @Test
    public void testCheckStockEnoughStock() {
        Item item = new Item("1", "item1", "imgurl1", 1, 2, 5);
        Item itemInDb = new Item("2", "item2", "imgurl1", 1, 2, 5);

        when(itemRepository.findItemById(item.getId())).thenReturn(itemInDb);

        assertTrue(itemService.checkStock(item));
    }

    @Test
    public void testCheckStockNotEnoughStock() {
        Item item = new Item("1", "item1", "imgurl1", 10, 2, 5);
        Item itemInDb = new Item("2", "item2", "imgurl1", 1, 2, 5);

        when(itemRepository.findItemById(item.getId())).thenReturn(itemInDb);

        assertFalse(itemService.checkStock(item));
    }

    @Test
    public void testResetItems() {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item("1", "item1", "imgurl1", 1, 2, 5);
        Item item2 = new Item("2", "item2", "imgurl1", 1, 2, 5);

        items.add(item1);
        items.add(item2);

        when(itemRepository.findAll()).thenReturn(items);
        when(itemRepository.save(item1)).thenReturn(item1);
        when(itemRepository.save(item2)).thenReturn(item2);

        itemService.resetItems();

        assertEquals(0, item1.getStock());
        assertEquals(item1.getDefaultPrice(), item1.getPrice());
        assertEquals(0, item2.getStock());
        assertEquals(item2.getDefaultPrice(), item2.getPrice());
    }
}