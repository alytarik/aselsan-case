package com.alitarik.aselsanbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.service.MachineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Machine", description = "The Machine API")
@RestController
@RequestMapping("/api/v1/admin")
public class MachineController {

    @Autowired
    private MachineService machineService;

    @Operation(summary = "Get machine status")
    @GetMapping("/machine")
    public Machine getMachine() {
        return machineService.getMachine();
    }

    @Operation(summary = "Collect money from machine")
    @GetMapping("/machine/collect")
    public Machine collectMoney() {
        return machineService.collectMoney();
    }

    @Operation(summary = "Reset machine, stocks and prices")
    @GetMapping("/machine/reset")
    public Machine resetMachine() {
        return machineService.resetMachine();
    }

    public Machine addMoney(Integer money) {
        return machineService.addMoney(money);
    }

}
