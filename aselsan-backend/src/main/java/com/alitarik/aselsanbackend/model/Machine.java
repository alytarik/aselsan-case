package com.alitarik.aselsanbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("machine")
public class Machine {
    @Id
    private String id;

    private int moneyCollected;

    public Machine(String id, int moneyCollected) {
        super();
        this.id = id;
        this.moneyCollected = moneyCollected;
    }

    // Getters
    public String getId() {
        return id;
    }

    public int getMoneyCollected() {
        return moneyCollected;
    }

    // Setters
    public void setMoneyCollected(int moneyCollected) {
        this.moneyCollected = moneyCollected;
    }
}