package com.alitarik.aselsanbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.model.ItemList;
import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.service.ItemService;
import com.alitarik.aselsanbackend.service.MachineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private MachineService machineService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderSuccess() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("1", "item1", "imgurl1", 1, 2, 5);
        items.add(item1);

        Item item2 = new Item("2", "item2", "imgurl2", 1, 10, 5);
        items.add(item2);

        ItemList itemList = new ItemList();
        itemList.setItems(items);

        Machine machine = new Machine("1", 100);

        when(itemService.checkStock(item1)).thenReturn(true);
        when(itemService.checkStock(item2)).thenReturn(true);
        when(itemService.getItemById(item1.getId())).thenReturn(item1);
        when(itemService.getItemById(item2.getId())).thenReturn(item2);
        when(itemService.updateItem(item1)).thenReturn(item1);
        when(itemService.updateItem(item2)).thenReturn(item2);
        when(machineService.addMoney(anyInt())).thenReturn(machine);
        ResponseEntity<?> response = orderController.createOrder(itemList);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreateOrderOutOfStock() {
        ArrayList<Item> items = new ArrayList<>();
        Item item1 = new Item("1", "item1", "imgurl1", 1, 2, 5);
        items.add(item1);

        Item item2 = new Item("2", "item2", "imgurl2", 1, 10, 5);
        items.add(item2);

        ItemList itemList = new ItemList(items);

        when(itemService.checkStock(item1)).thenReturn(true);
        when(itemService.checkStock(item2)).thenReturn(false);

        ResponseEntity<?> response = orderController.createOrder(itemList);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Item item2 is out of stock", response.getBody());
    }
}