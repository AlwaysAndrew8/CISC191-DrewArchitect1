package edu.sdccd.cisc191.template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The WeatherServlet class handles HTTP POST requests for weather data.
 * It connects to a WeatherServer to retrieve weather information for a given location.
 */
public class WeatherServlet extends HttpServlet {

    /**
     * Handles the HTTP POST request to fetch weather data.
     *
     * @param request  The HttpServletRequest object that contains the request the client has made of the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet sends to the client.
     * @throws ServletException If the request could not be handled.
     * @throws IOException      If an input or output error is detected when the servlet handles the request.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getParameter("location");
        WeatherClient client = new WeatherClient();
        try {
            client.startConnection("127.0.0.1", 4444);
            WeatherResponse weatherResponse = client.sendRequest(location);
            client.stopConnection();

            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.println("{\"temperature\":\"" + weatherResponse.getTemperature() + "\",\"humidity\":\"" + weatherResponse.getHumidity() + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}