package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WeatherClientNetworkingTest {

    @Test
    public void testStartConnection() {
        assertDoesNotThrow(() -> {
            try (ServerSocket serverSocket = new ServerSocket(4444)) {
                new Thread(() -> {
                    try {
                        Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            out.println("Mock response");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                WeatherClient client = new WeatherClient();
                client.startConnection("127.0.0.1", 4444);
                client.stopConnection();
            }
        });
    }

    @Test
    public void testStopConnection() {
        assertDoesNotThrow(() -> {
            try (ServerSocket serverSocket = new ServerSocket(4444)) {
                new Thread(() -> {
                    try {
                        Socket socket = serverSocket.accept();
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            out.println("Mock response");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                WeatherClient client = new WeatherClient();
                client.startConnection("127.0.0.1", 4444);
                client.stopConnection();
            }
        });
    }
}