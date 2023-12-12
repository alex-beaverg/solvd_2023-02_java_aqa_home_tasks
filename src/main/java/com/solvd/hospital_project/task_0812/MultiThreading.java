package com.solvd.hospital_project.task_0812;

import java.util.concurrent.*;

import static com.solvd.hospital_project.hospital.util.Printers.*;

public class MultiThreading {
    public static volatile ConnectionPool connectionPool = ConnectionPool.getInstance(5);

    public void runMultiThreading() {
        PRINT2LN.info("Multithreading:\n");
        PRINTLN.info("Using implementing Runnable and extending Thread:");
        useImplementRunnableAndExtendThread();
        PRINTLN.info("Using separate threads, Thread Pool and limits with Connection Pool:");
        useSeparateThreadsAndThreadPoolAndConnectionPool();
        PRINTLN.info("Using limits with Connection Pool:");
        useConnectionPool();
        PRINTLN.info("Using limits with Thread Pool:");
        useThreadPool();
        PRINTLN.info("Using CompletableFuture with callback and limits with Thread Pool:");
        useCompletableFutureAndCallbackAndThreadPool();
        PRINTLN.info("Using CompletableFuture with callback and limits with Connection Pool:");
        useCompletableFutureAndCallbackAndConnectionPool();
    }

    private void useImplementRunnableAndExtendThread() {
        HelloRunnable helloRunnable = new HelloRunnable("Runnable");
        Thread threadHelloRunnable = new Thread(helloRunnable);
        threadHelloRunnable.start();
        HelloThread helloThread = new HelloThread("Thread");
        Thread threadHelloThread = new Thread(helloThread);
        threadHelloThread.start();
        try {
            Thread.sleep(1005); // it is used to complete previous block of code
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printFinish();
    }

    private void useSeparateThreadsAndThreadPoolAndConnectionPool() {
        Connection connection1 = connectionPool.getConnection();
        Thread threadConnection1 = new Thread(connection1);
        threadConnection1.start();
        Connection connection2 = connectionPool.getConnection();
        Thread threadConnection2 = new Thread(connection2);
        threadConnection2.start();
        Connection connection3 = connectionPool.getConnection();
        Thread threadConnection3 = new Thread(connection3);
        threadConnection3.start();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 1; i <= 4; i++) {
            executor.submit(connectionPool.getConnection());
        }
        executor.shutdown();
        waitUntilConnectionPoolIsFull();
    }

    private void useConnectionPool() {
        for (int i = 1; i <= 7; i++) {
            Connection connection = connectionPool.getConnection();
            Thread threadConnection = new Thread(connection);
            threadConnection.start();
        }
        waitUntilConnectionPoolIsFull();
    }

    private void useThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 7; i++) {
            executor.submit(new HelloRunnable(Integer.toString(i)));
        }
        executor.shutdown();
        waitUntilExecutorIsTerminated(executor);
    }

    private void useCompletableFutureAndCallbackAndThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 7; i++) {
            CompletableFuture
                    .runAsync(new Connection(i), executor)
                    .thenRun(() -> PRINTLN.info(" -> callback thenRun() after stopping connection"));
        }
        executor.shutdown();
        waitUntilExecutorIsTerminated(executor);
    }

    private void useCompletableFutureAndCallbackAndConnectionPool() {
        for (int i = 1; i <= 7; i++) {
            Connection connection = connectionPool.getConnection();
            CompletableFuture
                    .runAsync(connection)
                    .thenRun(() -> PRINTLN.info(" -> callback thenRun() after stopping connection"));
        }
        waitUntilConnectionPoolIsFull();
    }

    private void waitUntilConnectionPoolIsFull() {
        while (true) {
            if (connectionPool.getNumberOfAvailableConnections() == connectionPool.getPoolSize()) {
                break;
            }
        } // it is used to complete previous block of code
        printFinish();
    }

    private void waitUntilExecutorIsTerminated(ExecutorService executor) {
        while (true) {
            if (executor.isTerminated()) {
                break;
            }
        } // it is used to complete previous block of code
        printFinish();
    }

    private void printFinish() {
        PRINTLN.info("Finish\n");
    }
}