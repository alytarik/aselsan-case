package com.alitarik.aselsanbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.service.MachineService;

@RestController
@RequestMapping("/api/v1/admin")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/machine")
    public Machine getMachine() {
        return machineService.getMachine();
    }

    @GetMapping("/machine/collect")
    public Machine collectMoney() {
        return machineService.collectMoney();
    }

    @GetMapping("/machine/reset")
    public Machine resetMachine() {
        return machineService.resetMachine();
    }

    public Machine addMoney(Integer money) {
        return machineService.addMoney(money);
    }

}
