package ru.nsu.yukhnina;

import java.util.ArrayList;

/**
 * Пока я не сильно довольна реализацией, буду менять.
 * Класс, обрабатывающий массив целых чисел.
 */
public class PrimeNumThread {

    private int streamsCount;
    private ArrayList<Integer> numbers;
    private ArrayList<OneThread> threadsArrays;
    private Thread[] threads;

    /**
     * Конструктор принимает на вход массив чисел,
     * который нужно проверить и количество потоков.
     * Также для каждого экземпляра хранится массив результатов;
     * вычислений подмассивов и массив нитей для проверки их завершения.
     */
    public PrimeNumThread(ArrayList<Integer> numbers, int streamsCount) {
        this.numbers = numbers;
        this.streamsCount = streamsCount;
        threadsArrays = new ArrayList<>();
        threads = new Thread[streamsCount];
    }

    /**
     * По кусочкам делим массив, сначала закидываем кусочки подлиннее,
     * потом покороче, отдельно проверяем если потоков больше чем чисел,
     * обрабатываем отдельно.
     * *когда пытаются запросить больше потоков чем чисел в массиве,
     * обраьатываю в один поток.
     */
    public boolean checkStreams() throws InterruptedException {
        int arrayLen = numbers.size() / streamsCount;
        int countTail = numbers.size() % streamsCount;
        int currStart = 0;
        //костыль, когда пытаются запросить больше потоков чем чисел в массиве,
        // обраьатываю в один поток
        if (arrayLen < streamsCount) {
            OneThread newThread = new OneThread(numbers, 0, numbers.size());
            threadsArrays.add(newThread);
            Thread childThread = new Thread(newThread);
            threads[0] = childThread;
            childThread.start();
        }

        //запускаю первые кусочки массива длины arrayLen+1.
        for (int i = 0; i < countTail; i++) {
            OneThread newThread = new OneThread(numbers, currStart, arrayLen + 1);
            threadsArrays.add(newThread);
            Thread childThread = new Thread(newThread);
            threads[i] = childThread;
            childThread.start();
            currStart += arrayLen + 1;
        }

        for (int i = countTail; i < streamsCount; i++) {
            OneThread newThread = new OneThread(numbers, currStart, arrayLen);
            threadsArrays.add(newThread);
            Thread childThread = new Thread(newThread);
            threads[i] = childThread;
            childThread.start();
            currStart += arrayLen;
        }

        try {
            for (int i = 0; i < streamsCount; i++) {
                threads[i].join();
                if (!threadsArrays.get(i).isAllNumbersPrime()) {
                    //если нашёлся кусочек, где есть непростое число,
                    //то сразу прерываем все остальные процессы.
                    for (int j = i; j < streamsCount; j++) {
                        threads[i].interrupt();
                    }
                    return true;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Ой ой");
        }
        return false;
    }
}
