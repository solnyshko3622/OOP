package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * One task.
 */
public class QueueElement {
    private ArrayList<Integer> numbers;

    /**
     * Create and contain task.
     * Task is one array listю
     */
    private QueueElement(ArrayList<Integer> numbers) {
        this.numbers = numbers;
    }

    /**
     * Retunt new task.
     */
    public static QueueElement createQueueElement(ArrayList<Integer> numbers) {
        return new QueueElement(numbers);
    }

    /**
     * arrayList from task.
     */
    public ArrayList<Integer> getArrayList() {
        return numbers;
    }
}
