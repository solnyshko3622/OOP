package ru.nsu.yukhnina;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Courier extends Thread {
    long capacity;
    TaskQueue tasks;
    String name;
    private int deliveredPizzas;
    private static final Logger LOGGER = Logger.getLogger(TaskManager.class.getName());

    public Courier(long capacity, TaskQueue tasks, String name) {
        LOGGER.setLevel(Level.INFO);
        this.capacity = capacity;
        this.tasks = tasks;
        this.name = name;
        start();
    }

    @Override
    public void run() {
        LOGGER.info("Courier " + name + " start work");
        int weight = 0;
        long time = 0;
        while (!isInterrupted()) {
            Task currentTask;
            try {
                currentTask = tasks.getTask();
            } catch (InterruptedException e) {
                deliveredPizzas += weight;
                LOGGER.info("Courier " + name + " delvered");
                LOGGER.info("Courier " + name + " end work");
                return;
            }
            if (currentTask != null) {
                LOGGER.info("Courier " + name + " get " + currentTask.getPizza());
                weight += 1;
                time += currentTask.getTimeToDelivery();
            }
            if (weight == capacity) {
                try {
                    deliveredPizzas += weight;
                    LOGGER.info("Courier " + name + " delvered");
                    Thread.sleep(time);
                    time = 0;
                    weight = 0;
                } catch (InterruptedException e) {
                    System.out.println("упси вупси работаем без сна");
                }
            }
        }
        deliveredPizzas += weight;
        LOGGER.info("Courier " + name + " delvered");
        LOGGER.info("Courier " + name + " end work");
    }

    public int howPizzas() {
        return deliveredPizzas;
    }
}
