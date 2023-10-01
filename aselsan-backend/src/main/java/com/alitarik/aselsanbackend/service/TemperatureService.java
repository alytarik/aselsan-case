package com.alitarik.aselsanbackend.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TemperatureService {

    @Scheduled(fixedRate = 60000)
    public void checkTemperature() {
        System.out.println("====================================");
        System.out.println("Checking the temperature");
        int temperature = (int) (Math.random() * 5 + 16);
        int idealTemperature = 18;
        System.out.println("Temperature is " + (temperature - idealTemperature) + " C off.");
        if (temperature - idealTemperature < -1) {
            System.out.println("Turning off the cooler");
        } else if (temperature - idealTemperature > 1) {
            System.out.println("Turning on the cooler");
        } else {
            System.out.println("Temperature is ideal");
        }
        System.out.println("====================================");
    }
}
