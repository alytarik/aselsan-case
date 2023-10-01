package com.alitarik.aselsanbackend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.alitarik.aselsanbackend.model.Machine;

public interface MachineRepository extends MongoRepository<Machine, String> {

}