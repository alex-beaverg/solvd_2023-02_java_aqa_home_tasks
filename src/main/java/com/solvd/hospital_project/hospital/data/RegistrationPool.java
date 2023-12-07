package com.solvd.hospital_project.hospital.data;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RegistrationPool {
    private static volatile RegistrationPool instance;
    private final int poolSize;
    private final Queue<Registration> registrations = new ConcurrentLinkedQueue<>();

    private RegistrationPool(int poolSize) {
        this.poolSize = poolSize;
        for (int i = 0; i < this.poolSize; i++) {
            registrations.add(new Registration(i + 1));
        }
    }

    public static synchronized RegistrationPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new RegistrationPool(poolSize);
        }
        return instance;
    }

    public synchronized Registration getRegistration() {
        while (registrations.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return registrations.remove();
    }

    public synchronized void releaseRegistration(Registration registration) {
        if (registrations.size() < this.poolSize) {
            registrations.add(registration);
        }
        notify();
    }

    public synchronized int getNumberOfAvailableRegistrations() {
        return registrations.size();
    }

    public synchronized int getPoolSize() {
        return this.poolSize;
    }
}
