package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The WeatherResponse class represents a weather response with a location, temperature, and humidity.
 * It provides methods to convert the response to and from JSON format.
 */
public class WeatherResponse {
    private String location;
    private String temperature;
    private String humidity;

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Converts a WeatherResponse object to its JSON representation.
     *
     * @param response The WeatherResponse object to be converted.
     * @return The JSON string representation of the WeatherResponse object.
     * @throws Exception If an error occurs during conversion.
     */
    public static String toJSON(WeatherResponse response) throws Exception {
        return objectMapper.writeValueAsString(response);
    }

    /**
     * Converts a JSON string to a WeatherResponse object.
     *
     * @param input The JSON string to be converted.
     * @return The WeatherResponse object.
     * @throws Exception If an error occurs during conversion.
     */
    public static WeatherResponse fromJSON(String input) throws Exception {
        return objectMapper.readValue(input, WeatherResponse.class);
    }

    /**
     * Default constructor for WeatherResponse.
     * Initializes temperature and humidity to "N/A".
     */
    protected WeatherResponse() {
        this.temperature = "N/A";
        this.humidity = "N/A";
    }

    /**
     * Constructs a WeatherResponse object with the specified location, temperature, and humidity.
     *
     * @param location    The location of the weather response.
     * @param temperature The temperature of the weather response.
     * @param humidity    The humidity of the weather response.
     */
    public WeatherResponse(String location, String temperature, String humidity) {
        this.location = location;
        this.temperature = temperature != null ? temperature : "N/A";
        this.humidity = humidity != null ? humidity : "N/A";
    }

    /**
     * Gets the location of the weather response.
     *
     * @return The location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets the temperature of the weather response.
     *
     * @return The temperature.
     */
    public String getTemperature() {
        return temperature;
    }

    /**
     * Gets the humidity of the weather response.
     *
     * @return The humidity.
     */
    public String getHumidity() {
        return humidity;
    }

    /**
     * Sets the location of the weather response.
     *
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the temperature of the weather response.
     *
     * @param temperature The temperature to set.
     */
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    /**
     * Sets the humidity of the weather response.
     *
     * @param humidity The humidity to set.
     */
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}