package com.alitarik.aselsanbackend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.service.MachineService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MachineControllerTest {

    @Mock
    private MachineService machineService;

    @InjectMocks
    private MachineController machineController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMachine() {
        Machine expectedMachine = new Machine("1", 100);

        when(machineService.getMachine()).thenReturn(expectedMachine);

        Machine actualMachine = machineController.getMachine();

        assertEquals(expectedMachine.getId(), actualMachine.getId());
        assertEquals(expectedMachine.getMoneyCollected(), actualMachine.getMoneyCollected());
    }

    @Test
    public void testCollectMoney() {
        when(machineService.collectMoney()).thenReturn(new Machine("1", 0));

        Machine actualMachine = machineController.collectMoney();

        assertEquals(0, actualMachine.getMoneyCollected());
    }

    @Test
    public void testResetMachine() {
        when(machineService.resetMachine()).thenReturn(new Machine("1", 0));
    
        Machine actualMachine = machineController.resetMachine();
    
        assertEquals(0, actualMachine.getMoneyCollected());
    }

    @Test
    public void testAddMoney() {
        Machine originalMachine = new Machine("1", 100);
        Machine expectedMachine = new Machine("1", 150);

        when(machineService.addMoney(50)).thenReturn(expectedMachine);

        Machine actualMachine = machineController.addMoney(50);

        assertEquals(expectedMachine.getId(), actualMachine.getId());
        assertEquals(originalMachine.getMoneyCollected() + 50, actualMachine.getMoneyCollected());
    }
}