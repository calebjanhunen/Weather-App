package weatherapp;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HTTPGetRequest getRequest = new HTTPGetRequest("Kitchener");

        String weatherInfo = getRequest.getWeatherInfo();

        WeatherAppParser parser = new WeatherAppParser(getRequest.getWeatherInfo());

        HashMap<String, String> loc = parser.createLocationMap();
        HashMap<String, String> weather = parser.createWeatherMap();

        System.out.println(loc.get("name"));
        System.out.println(weather.get("temp_c") + "°");
    }
}
