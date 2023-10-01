package com.alitarik.aselsanbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("items")
public class Item {
    @Id
    private String id;

    private String name;
    private String imgURL;
    private int stock;
    private int price;

    public Item(String id, String name, String imgURL, int stock, int price) {
        super();
        this.id = id;
        this.name = name;
        this.imgURL = imgURL;
        this.stock = stock;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgURL() {
        return imgURL;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}