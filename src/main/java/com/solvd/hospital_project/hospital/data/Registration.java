package com.solvd.hospital_project.hospital.data;

import java.util.Random;

import static com.solvd.hospital_project.hospital.util.Printers.PRINTLN;

public class Registration implements Runnable {
    private final int number;

    public Registration(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        PRINTLN.info("Registration " + number + " started");
        int duration = new Random().nextInt(1000);
        try {
            Thread.sleep(duration); // it is used for visualization
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PRINTLN.info("Registration " + number + " finished (duration = " + duration + "ms)");
        Creator.registrationPool.releaseRegistration(this);
    }
}
