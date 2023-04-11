package behavioral.observer;

public class Test {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        WeatherDisplay weatherDisplay = new WeatherDisplay(weatherData);
        weatherData.setMessurements(37.2f,80f,32.5f);
        weatherDisplay.display();
    }
}
