package com.solvd.hospital_project.task_0812;

import java.util.Random;

import static com.solvd.hospital_project.hospital.util.Printers.PRINTLN;

public class Connection implements Runnable {
    private final int number;

    public Connection(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        PRINTLN.info("Connection " + number + " started");
        int duration = new Random().nextInt(1000);
        try {
            Thread.sleep(duration); // it is used for visualization
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PRINTLN.info("Connection " + number + " stopped (duration = " + duration + "ms)");
        MultiThreading.connectionPool.releaseConnection(this);
    }
}
