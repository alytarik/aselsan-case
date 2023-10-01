package com.alitarik.aselsanbackend.model;

import java.util.List;

public class ItemList {
    private List<Item> items;

    public ItemList() {
        super();
    }

    public ItemList(List<Item> items) {
        super();
        this.items = items;
    }

    // Getters
    public List<Item> getItems() {
        return items;
    }

    // Setters
    public void setItems(List<Item> items) {
        this.items = items;
    }
}