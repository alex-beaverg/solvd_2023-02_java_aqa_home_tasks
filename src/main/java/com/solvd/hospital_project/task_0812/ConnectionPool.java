package com.solvd.hospital_project.task_0812;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConnectionPool {
    private static volatile ConnectionPool instance;
    private final int poolSize;
    private final Queue<Connection> connections = new ConcurrentLinkedQueue<>();

    private ConnectionPool(int poolSize) {
        this.poolSize = poolSize;
        for (int i = 0; i < this.poolSize; i++) {
            connections.add(new Connection(i + 1));
        }
    }

    public static synchronized ConnectionPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new ConnectionPool(poolSize);
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        while (connections.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return connections.remove();
    }

    public synchronized void releaseConnection(Connection connection) {
        if (connections.size() < this.poolSize) {
            connections.add(connection);
        }
        notify();
    }

    public synchronized int getNumberOfAvailableConnections() {
        return connections.size();
    }

    public synchronized int getPoolSize() {
        return this.poolSize;
    }
}
