package ru.nsu.yukhnina;

/**
 * Class described one order.
 */
public class Task {
    private String pizza;
    private String address;
    private long timeToDelivery;
    private int taskId;

    /**
     * pizza - pizza name.
     * address - addres to deliver.
     * time - time to deliver.
     * taskId - id task to check it in logger;
     * Described task.
     */
    public Task(String pizza, String address, long time, int taskId) {
        this.pizza = pizza;
        this.address = address;
        timeToDelivery = time;
        this.taskId = taskId;
    }

    /**
     * Getter to taskId.
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * Getter to delivered time.
     */
    public long getTimeToDelivery() {
        return timeToDelivery;
    }

    /**
     * Getter to pizza name.
     */
    public String getPizza() {
        return pizza;
    }

    /**
     * Getter to order address.
     */
    public String getAddress() {
        return address;
    }
}
