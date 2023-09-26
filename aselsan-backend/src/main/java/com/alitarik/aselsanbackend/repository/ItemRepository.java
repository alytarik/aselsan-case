package com.alitarik.aselsanbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.alitarik.aselsanbackend.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {

    @Query("{name:'?0'}")
    Item findItemByName(String name);

    @Query("{id:'?0'}")
    Item findItemById(String id);

    public long count();

}