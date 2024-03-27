package ru.nsu.yukhnina;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * Создаёт задачки,
 * отправляет широковещательный запрос на соединение,
 * ждёт соединившихся и для каждого запускает нить для подсчётов.
 */
@SuppressWarnings("ALL")
public class Sender {
    private ArrayList<Integer> numbers;
    private int serverSocketPort;
    private int datagrammSocketPort;
    int datagrammPacketPort;
    String serverSocketHost;
    private boolean isPrime;

    /**
     * Хранит все штучки для соединения,
     * делит большой массив numbers на кусочки-таски.
     */
    public Sender(
            ArrayList<Integer> numbers,
            int serverSocketPort,
            int datagrammSocketPort,
            int datagrammPacketPort,
            String serverSocketHost) {
        this.numbers = numbers;
        this.serverSocketPort = serverSocketPort;
        this.datagrammSocketPort = datagrammSocketPort;
        this.datagrammPacketPort = datagrammPacketPort;
        this.serverSocketHost = serverSocketHost;
        startWorkSender();
    }

    /**
     * Return esult of counting.
     */
    public boolean isArrayPrime() {
        return isPrime;
    }

    /**
     * Создаёт таски,
     * соединяется со всеми,
     * отправляет тасочки соединённым.
     */
    public void startWorkSender() {
        final int step = 5;
        ServerSocket serverSocket = null;
        ArrayList<Planer> threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(
                    serverSocketPort,
                    50,
                    InetAddress.getByName("localhost"));
            DatagramSocket datagramSocket = new DatagramSocket(datagrammSocketPort);
            byte[] sendDataPort = "8888".getBytes();
            DatagramPacket packet = new DatagramPacket(
                    sendDataPort, sendDataPort.length,
                    InetAddress.getByName(serverSocketHost),
                    datagrammPacketPort);
            datagramSocket.send(packet);
            datagramSocket.close();
            ConcurrentLinkedQueue<QueueElement> taskQueue = new ConcurrentLinkedQueue<>();
            int i;
            for (i = 0; i <= numbers.size() - step; i += step) {
                taskQueue.add(QueueElement.createQueueElement(
                        new ArrayList<>(numbers.subList(i, i + step))));
            }
            if (numbers.size() % 5 != 0) {
                taskQueue.add(QueueElement.createQueueElement(
                        new ArrayList<>(
                                numbers.subList(numbers.size()
                                        - numbers.size()
                                        % 5, numbers.size()))));

            }
            while (true) {
                Socket acceptSocket = serverSocket.accept();
                threads.add(new Planer(taskQueue, serverSocket, acceptSocket));
            }
        } catch (IOException e) {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException ex) {
                System.out.println();
            }
            for (Planer thread : threads) {
                if (!thread.getResult()) {
                    boolean isPrime = false;
                    return;
                }
            }
            isPrime = true;
        }
    }
}
