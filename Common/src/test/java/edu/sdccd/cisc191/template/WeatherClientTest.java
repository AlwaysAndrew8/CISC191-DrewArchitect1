package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WeatherClientTest {

    @Test
    public void testWeatherRequestToJson() throws Exception {
        WeatherRequest request = new WeatherRequest("San Diego");
        String json = WeatherRequest.toJSON(request);
        assertNotNull(json);
    }

    @Test
    public void testWeatherResponseFromJson() throws Exception {
        String json = "{\"temperature\":\"25°C\",\"humidity\":\"60%\"}";
        WeatherResponse response = WeatherResponse.fromJSON(json);
        assertEquals("25°C", response.getTemperature());
        assertEquals("60%", response.getHumidity());
    }
}
