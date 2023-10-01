package com.alitarik.aselsanbackend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alitarik.aselsanbackend.model.Machine;
import com.alitarik.aselsanbackend.repository.MachineRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MachineServiceTest {

    @Mock
    private MachineRepository machineRepository;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private MachineService machineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMachines() {
        Machine machine = new Machine("1", 100);
        when(machineRepository.findAll()).thenReturn(Arrays.asList(machine));
        List<Machine> machines = machineService.getAllMachines();
        assertEquals(1, machines.size());
        assertEquals(machine, machines.get(0));
    }

    @Test
    void testGetMachine() {
        Machine machine = new Machine("1", 100);
        when(machineRepository.findAll()).thenReturn(Arrays.asList(machine));
        assertEquals(machine, machineService.getMachine());
    }

    @Test
    void testAddMoney() {
        Machine machine = new Machine("1", 100);
        when(machineRepository.findAll()).thenReturn(Arrays.asList(machine));
        when(machineRepository.save(any(Machine.class))).thenAnswer(i -> i.getArguments()[0]);
        Machine updatedMachine = machineService.addMoney(10);
        assertEquals(110, updatedMachine.getMoneyCollected());
    }

    @Test
    void testCollectMoney() {
        Machine oldMachine = new Machine("1", 100);
        when(machineRepository.findAll()).thenReturn(Arrays.asList(oldMachine));
        when(machineRepository.save(any(Machine.class))).thenAnswer(i -> i.getArguments()[0]);
        Machine updatedMachine = machineService.collectMoney();
        assertEquals(0, updatedMachine.getMoneyCollected());
    }

    @Test
    void testResetMachine() {
        Machine machine = new Machine("1", 100);
        when(machineRepository.findAll()).thenReturn(Arrays.asList(machine));
        when(machineRepository.save(any(Machine.class))).thenAnswer(i -> i.getArguments()[0]);
        doNothing().when(itemService).resetItems();
        Machine updatedMachine = machineService.resetMachine();
        assertEquals(0, updatedMachine.getMoneyCollected());
    }
}