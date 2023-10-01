package com.alitarik.aselsanbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.repository.MachineRepository;

@Service
public class MachineService {

    @Autowired
    private MachineRepository MachineRepository;

    @Autowired
    private ItemService itemService;

    public List<Machine> getAllMachines() {
        return MachineRepository.findAll();
    }

    public Machine getMachine() {
        return MachineRepository.findAll().get(0);
    }

    public Machine addMoney(Integer money) {
        Machine machine = MachineRepository.findAll().get(0);
        machine.setMoneyCollected(machine.getMoneyCollected() + money);
        return MachineRepository.save(machine);
    }

    public Machine collectMoney() {
        Machine machine = MachineRepository.findAll().get(0);
        machine.setMoneyCollected(0);
        return MachineRepository.save(machine);
    }

    public Machine resetMachine() {
        itemService.resetItems();
        Machine machine = MachineRepository.findAll().get(0);
        machine.setMoneyCollected(0);
        return MachineRepository.save(machine);
    }
}
