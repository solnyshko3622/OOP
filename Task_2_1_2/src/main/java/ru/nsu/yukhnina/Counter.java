package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * считает массивчик все ли числа в нъм простые.
 */
public class Counter {
    private ArrayList<Integer> numbers;

    /**
     * Хранит один массивчик.
     */
    public Counter(ArrayList<Integer> numbers) {
        this.numbers =  numbers;
    }

    /**
     * Считает массивчик.
     */
    public boolean countPrime() {
        for (Integer i : numbers) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
