package com.alitarik.aselsanbackend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.alitarik.aselsanbackend.controller.ItemController;
import com.alitarik.aselsanbackend.model.Item;
import com.alitarik.aselsanbackend.service.ItemService;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("1", "item1", "imgurl1", 1, 2, 5));
        items.add(new Item("2", "item2", "imgurl2", 3, 4, 8));
        when(itemService.getAllItems()).thenReturn(items);

        List<Item> result = itemController.getAllItems();

        assertThat(result).isEqualTo(items);
    }

    @Test
    public void testGetItemById() {
        Item item = new Item("1", "item1", "imgurl1", 1, 2, 5);

        when(itemService.getItemById("1")).thenReturn(item);

        Item result = itemController.getItemById("1");

        assertThat(result).isEqualTo(item);
    }

    @Test
    public void testCreateItem() {
        Item item = new Item("1", "item1", "imgurl1", 1, 2, 5);
        when(itemService.createItem(any(Item.class))).thenReturn(item);

        Item result = itemController.createItem(item);

        assertThat(result).isEqualTo(item);
    }

    @Test
    public void testUpdateItem() {
        Item item = new Item("1", "item1", "imgurl1", 1, 2, 5);
        when(itemService.updateItem(anyString(), any(Item.class))).thenReturn(item);

        Item result = itemController.updateItem("1", item);

        assertThat(result).isEqualTo(item);
    }

    @Test
    public void testDeleteItem() {
        doNothing().when(itemService).deleteItem(anyString());

        ResponseEntity<?> result = itemController.deleteItem("1");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(itemService).deleteItem("1");
    }

}