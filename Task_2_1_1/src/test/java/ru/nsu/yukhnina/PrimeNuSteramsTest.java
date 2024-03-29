package ru.nsu.yukhnina;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PrimeNuSteramsTest {
    @Test
    void easyTest() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(7, 2, 3, 5));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertFalse(test.checkParallel());
    }

    @Test
    void easyTest2() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(7, 2, 3, 5, 8));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertTrue(test.checkParallel());
    }

    @Test
    void easyTest3() {
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(2, 3, 5, 10));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertTrue(test.checkParallel());
    }

    @Test
    void testFromTask() {
        //[6, 8, 7, 13, 5, 9, 4]
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(6, 8, 7, 13, 5, 9, 4));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertTrue(test.checkParallel());
    }

    @Test
    void testFromTask2() {
        //[20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
        //6998009, 6998029, 6998039, 20165149, 6998051, 6998053]
        ArrayList<Integer> testList =
                new ArrayList<>(Arrays.asList(20319251, 6997901, 6997927,
                        6997937, 17858849, 6997967,
                        6998009, 6998029, 6998039,
                        20165149, 6998051, 6998053));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertFalse(test.checkParallel());
    }

    //неравномерные потоки
    @Test
    void bigNum() {
        ArrayList<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testList.add(2);
        }
        for (int i = 0; i < 9000; i++) {
            testList.add(165523);
        }
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertFalse(test.checkParallel());
    }

    //неравномерные потоки
    @Test
    void bigNum2() {
        ArrayList<Integer> testList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            testList.add(2);
        }
        for (int i = 0; i < 9000; i++) {
            testList.add(165523);
        }
        testList.add(32);
        PrimeNumParallelStream test = new PrimeNumParallelStream(testList);
        assertTrue(test.checkParallel());
    }

    @Test
    void testForGraph() {
        long startTime = System.currentTimeMillis();
        Integer[] testList = new Integer[20_000_000];
        Arrays.fill(testList, 165523);
        ArrayList<Integer> testArrayList = new ArrayList<>(Arrays.asList(testList));
        PrimeNumParallelStream test = new PrimeNumParallelStream(testArrayList);
        assertFalse(test.checkParallel());
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;
        System.out.println(timeElapsed);
    }
}