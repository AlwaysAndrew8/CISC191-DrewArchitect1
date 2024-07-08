package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;

/**
 * The WeatherClient class establishes a connection to a weather server,
 * sends a request for weather data, and receives the response.
 */

public class WeatherClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Starts a connection to the server with the specified IP address and port.
     *
     * @param ip The IP address of the server.
     * @param port The port number of the server.
     * @throws IOException If an I/O error occurs when creating the socket.
     */

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    /**
     * Sends a weather request for the given location to the server and retrieves the response.
     *
     * @param location The desired location for weather information.
     * @return The weather data received from the server.
     * @throws Exception If an error occurs during communication or data processing.
     */

    public WeatherResponse sendRequest(String location) throws Exception {
        WeatherRequest request = new WeatherRequest(location);
        out.println(WeatherRequest.toJSON(request));
        return WeatherResponse.fromJSON(in.readLine());
    }

    /**
     * Stops the connection to the server by closing the input and output streams
     * and the socket.
     *
     * @throws IOException If an I/O error occurs when closing the socket or streams.
     */

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * The main method to run the WeatherClient. It establishes a connection to the server,
     * sends a weather request for "San Diego", and prints the temperature and humidity
     * from the response.
     *
     * @param args Command line arguments (not used).
     */

    public static void main(String[] args) {
        WeatherClient client = new WeatherClient();
        try {
            client.startConnection("127.0.0.1", 4444);

            WeatherResponse response = client.sendRequest("San Diego");
            System.out.println(response.getTemperature() + ", " + response.getHumidity());

            client.stopConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}