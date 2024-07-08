package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The WeatherServer class manages the server-side operations, including accepting client connections,
 * handling client requests, and fetching weather data from an external API.
 */
public class WeatherServer {
    private ServerSocket serverSocket;

    /**
     * Starts the server on the specified port.
     *
     * @param port The port number to start the server on.
     * @throws Exception If an error occurs while starting the server.
     */
    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            handleClient(clientSocket);
        }
    }

    /**
     * Handles a client connection, processes the request, and sends back the weather data response.
     *
     * @param clientSocket The socket representing the client connection.
     * @throws Exception If an error occurs while handling the client.
     */
    private void handleClient(Socket clientSocket) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            WeatherRequest request = WeatherRequest.fromJSON(inputLine);
            WeatherResponse response = fetchWeatherData(request);
            out.println(WeatherResponse.toJSON(response));
        }

        in.close();
        out.close();
        clientSocket.close();
    }

    /**
     * Fetches weather data from an external API based on the provided request.
     *
     * @param request The weather request containing the location information.
     * @return The weather response containing the fetched weather data.
     */
    private WeatherResponse fetchWeatherData(WeatherRequest request) {
        String apiKey = "a7c83ad3ee3d251e6683b114d66938bf";  // Replace with your actual API key
        String location = request.getLocation();
        String urlString = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,CA,US&appid=%s&units=imperial", location, apiKey);

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            conn.disconnect();

            // Parse JSON response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(content.toString());
            String temperature = rootNode.path("main").path("temp").asText() + "°F";
            String humidity = rootNode.path("main").path("humidity").asText() + "%";

            return new WeatherResponse(location, temperature, humidity);
        } catch (Exception e) {
            e.printStackTrace();
            return new WeatherResponse(location, "N/A", "N/A");
        }
    }

    /**
     * The entry point of the WeatherServer application. It starts the server on port 4444.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        WeatherServer server = new WeatherServer();
        try {
            server.start(4444);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}