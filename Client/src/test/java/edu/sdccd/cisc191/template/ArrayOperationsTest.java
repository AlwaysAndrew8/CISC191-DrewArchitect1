package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArrayOperationsTest {
    private WeatherClientGUI weatherClientGUI;

    @BeforeEach
    public void setUp() {
        weatherClientGUI = new WeatherClientGUI();
    }

    @Test
    public void testStoreWeatherData() {
        WeatherResponse response1 = new WeatherResponse("Location1", "75°F", "50%");
        WeatherResponse response2 = new WeatherResponse("Location2", "80°F", "55%");
        weatherClientGUI.storeWeatherData(response1);
        weatherClientGUI.storeWeatherData(response2);

        assertEquals(2, weatherClientGUI.getWeatherData().size());
        assertEquals(response2, weatherClientGUI.getWeatherData().getFirst());
        assertEquals(response1, weatherClientGUI.getWeatherData().getLast());
    }

    @Test
    public void testStoreWeatherData_MaxSize() {
        for (int i = 0; i < 12; i++) {
            WeatherResponse response = new WeatherResponse("Location" + i, "75°F", "50%");
            weatherClientGUI.storeWeatherData(response);
        }

        assertEquals(10, weatherClientGUI.getWeatherData().size());
        assertEquals("Location11", weatherClientGUI.getWeatherData().getFirst().getLocation());
        assertEquals("Location2", weatherClientGUI.getWeatherData().getLast().getLocation());
    }

    @Test
    public void testDisplayWeatherData() {
        WeatherResponse response1 = new WeatherResponse("Location1", "75°F", "50%");
        WeatherResponse response2 = new WeatherResponse("Location2", "80°F", "55%");
        weatherClientGUI.storeWeatherData(response1);
        weatherClientGUI.storeWeatherData(response2);

        String expectedOutput = "Stored Weather Data:\n"
                + "Location: Location2, Temperature: 80°F, Humidity: 55%\n"
                + "Location: Location1, Temperature: 75°F, Humidity: 50%\n";

        assertEquals(expectedOutput, weatherClientGUI.getWeatherDataString());
    }
}