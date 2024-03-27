package ru.nsu.yukhnina;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

/**
 * Планер нить получает соединённый с сервером сокет,
 * отправляет задачу на Reciver.
 * Ждёт ответ и если очередь не пустая даёт подключённому ресиверу задачу.
 */
public class Planer extends Thread {
    private final ServerSocket serverSocket;
    private Queue<QueueElement> tasksQueue;
    private final Socket acceptSocket;
    private boolean result;

    /**
     * Хранит 2 сокета, accept чтобы отправлять данные,
     * сервер чтобы закрыть при косяках с подключением;
     * или когда все таски посчитались.
     */
    public Planer(Queue<QueueElement> tasksQueue,
                  ServerSocket serverSocket,
                  Socket acceptSocket) {
        this.acceptSocket = acceptSocket;
        this.tasksQueue = tasksQueue;
        this.serverSocket = serverSocket;
        this.result = true;
        start();
    }

    /**
     * Если хотя бы один раз сервер выдал false, он выдаст false.
     */
    public boolean getResult() {
        return result;
    }

    /**
     * Отправляет на сервер данные которые нужно посчитать и ждёт ответ.
     */
    @Override
    public void run() {
        QueueElement q = null;
        while (!tasksQueue.isEmpty()) {
            try {
                q = tasksQueue.poll();
                if (q == null) {
                    break;
                }
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(acceptSocket.getOutputStream()));
                ObjectMapper objectMapper = new ObjectMapper();
                out.write(objectMapper.writeValueAsString(q.getArrayList()) + "\n");
                out.flush();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(acceptSocket.getInputStream()));
                result = objectMapper.readValue(in.readLine(), Boolean.class);
                if (!result) {
                    serverSocket.close();
                    acceptSocket.close();
                    break;
                }
                q = null;
            } catch (IOException e) {
                System.out.println("Проблемы с accept socket");
                tasksQueue.add(q);
                try {
                    acceptSocket.close();
                } catch (IOException ex) {
                    System.out.println("Не получилось закрыть accept socket, всё плохо");
                    throw new RuntimeException(ex);
                }
            }
        }
        try {
            acceptSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Не получилось закрыть");
            throw new RuntimeException(e);
        }
    }
}
