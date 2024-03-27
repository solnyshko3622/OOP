package ru.nsu.yukhnina;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


/**
 * получает сообщение из sendera,
 * соединяется с ним,
 * считает свою таску, отправляет ответ.
 */
@SuppressWarnings("ALL")
public class Receiver {
    private final int port;
    private final String host;

    /**
     * Хранит порты для подключения и начинает работу.
     */
    public Receiver(int port, String host) {
        this.port = port;
        this.host = host;
        startWork();
    }

    /**
     * получает сообщение для соединеия,
     * соединяется,
     * ждёт таску для подсчёта,
     * считается.
     * Заканчивает работу когда планер закрывает соединение.
     */
    public void startWork() {
        Socket socket = null;
        try (MulticastSocket datagrammSocket = new MulticastSocket(port)) {
            NetworkInterface netIf = NetworkInterface.getByName("bge0");
            datagrammSocket.joinGroup(
                    new InetSocketAddress(InetAddress.getByName(host), 0), netIf);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(
                    receiveBuffer, receiveBuffer.length);
            datagrammSocket.receive(receivePacket);

            String receivedMessage = new String(
                    receivePacket.getData(), 0, receivePacket.getLength());
            socket = new Socket(
                    InetAddress.getByName("localhost"), Integer.parseInt(receivedMessage));
            while (true) {

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream()));
                String message = in.readLine();
                if (message == null) {
                    socket.close();
                    break;
                }
                ObjectMapper objectMapper = new ObjectMapper();
                ArrayList<Integer> numbers = objectMapper.readValue(
                        message, ArrayList.class);
                Boolean result = new Counter(numbers).countPrime();
                out.write(objectMapper.writeValueAsString(result) + "\n");
                out.flush();

            }
        } catch (SocketException e) {
            System.out.println("Не могу создать датаграмм сокет");
        } catch (IOException e) {
            try {
                assert socket != null;
                socket.close();

            } catch (IOException ex) {
                System.out.println();
            }
        }
    }
}
