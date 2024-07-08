package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The WeatherRequest class represents a weather request with a location and temperature.
 * It provides methods to convert the request to and from JSON format.
 */
public class WeatherRequest {
    private String location;
    private String temperature;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a WeatherRequest object to its JSON representation.
     *
     * @param request The WeatherRequest object to be converted.
     * @return The JSON string representation of the WeatherRequest object.
     * @throws Exception If an error occurs during conversion.
     */
    public static String toJSON(WeatherRequest request) throws Exception {
        return objectMapper.writeValueAsString(request);
    }

    /**
     * Converts a JSON string to a WeatherRequest object.
     *
     * @param input The JSON string to be converted.
     * @return The WeatherRequest object.
     * @throws Exception If an error occurs during conversion.
     */
    public static WeatherRequest fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, WeatherRequest.class);
    }

    /**
     * Default constructor for WeatherRequest.
     * Protected to prevent direct instantiation.
     */
    protected WeatherRequest() {}

    /**
     * Constructs a WeatherRequest object with the specified location.
     *
     * @param location The location for the weather request.
     */
    public WeatherRequest(String location) {
        this.location = location;
    }

    /**
     * Gets the location of the weather request.
     *
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location of the weather request.
     *
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the temperature of the weather request.
     *
     * @return The temperature.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Sets the temperature of the weather request.
     *
     * @param temperature The temperature to set.
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}