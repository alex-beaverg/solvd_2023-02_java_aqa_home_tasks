package com.solvd.hospital_project.task_0812;

import java.util.Random;

import static com.solvd.hospital_project.hospital.util.Printers.PRINTLN;

public class HelloThread extends Thread {
    private final String title;

    public HelloThread(String title) {
        this.title = title;
    }

    @Override
    public void run() {
        PRINTLN.info("Hello " + title + " started");
        int duration = new Random().nextInt(1000);
        try {
            Thread.sleep(duration); // used for visualization
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        PRINTLN.info("Hello " + title + " stopped (duration = " + duration + "ms)");
    }
}
