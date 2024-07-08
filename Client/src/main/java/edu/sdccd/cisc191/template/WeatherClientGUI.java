package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedList;

/**
 * The WeatherClientGUI class provides a graphical user interface for fetching and displaying weather data.
 * It connects to a WeatherServer to retrieve weather information for a given location.
 */
public class WeatherClientGUI extends Application {
    private WeatherClient client = new WeatherClient();
    private LinkedList<WeatherResponse> weatherData = new LinkedList<>();
    private static final int MAX_SIZE = 10;

    /**
     * The main entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather Client");

        // GUI components - weather data
        Label locationLabel = new Label("Enter location:");
        TextField locationField = new TextField();
        Button fetchButton = new Button("Fetch Weather");
        Label weatherLabel = new Label();

        fetchButton.setOnAction(e -> {
            try {
                client.startConnection("127.0.0.1", 4444);
                WeatherResponse response = client.sendRequest(locationField.getText());
                client.stopConnection();
                storeWeatherData(response);
                displayWeatherData();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Label arrayLabel = new Label("Stored Weather Data:");
        Button printAllButton = new Button("Print All Weather Data");

        printAllButton.setOnAction(e -> displayWeatherData());

        grid.add(arrayLabel, 0, 0);
        grid.add(printAllButton, 0, 1);

        VBox vbox = new VBox(locationLabel, locationField, fetchButton, weatherLabel, grid);
        Scene scene = new Scene(vbox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Stores the fetched weather data in the list.
     * If the list exceeds the maximum size, it removes the oldest entry.
     *
     * @param response The WeatherResponse object containing the weather data to be stored.
     */
    public void storeWeatherData(WeatherResponse response) {
        if (weatherData.size() >= MAX_SIZE) {
            weatherData.removeLast(); // Remove the oldest entry
        }
        weatherData.addFirst(response); // Add the new entry at the top
    }

    /**
     * Displays the stored weather data in an alert dialog.
     */
    public void displayWeatherData() {
        showAlert(getWeatherDataString());
    }

    /**
     * Gets the list of stored weather data.
     *
     * @return The LinkedList of WeatherResponse objects.
     */
    public LinkedList<WeatherResponse> getWeatherData() {
        return weatherData;
    }

    /**
     * Gets the stored weather data as a formatted string.
     *
     * @return A string representation of the stored weather data.
     */
    public String getWeatherDataString() {
        StringBuilder output = new StringBuilder("Stored Weather Data:\n");
        for (WeatherResponse response : weatherData) {
            output.append("Location: ").append(response.getLocation())
                    .append(", Temperature: ").append(response.getTemperature())
                    .append(", Humidity: ").append(response.getHumidity()).append("\n");
        }
        return output.toString();
    }

    /**
     * Displays an alert dialog with the specified message.
     *
     * @param message The message to be displayed in the alert dialog.
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * The main entry point for the WeatherClientGUI application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }
}