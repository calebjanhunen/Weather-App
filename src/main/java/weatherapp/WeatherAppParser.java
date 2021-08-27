package weatherapp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.Map;

public class WeatherAppParser {
    JSONObject jsonLocation;
    JSONObject jsonWeather;

    public WeatherAppParser(String weatherInfo) {
        parse(weatherInfo);
    }

    private void parse(String jsonWeatherInfo) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(jsonWeatherInfo);
            JSONObject jsonObj = (JSONObject) obj;

            //extract location info from json
            jsonLocation = (JSONObject) jsonObj.get("location");

            //extract weather info from json
            jsonWeather = (JSONObject) jsonObj.get("current");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public HashMap<String, String> createLocationMap () {
        HashMap<String, String> locationMap = new HashMap<>();

        locationMap.put("name", getLocation().get("name").toString());
        locationMap.put("region", getLocation().get("region").toString());
        locationMap.put("country", getLocation().get("country").toString());
        locationMap.put("localtime", getLocation().get("localtime").toString());
        return locationMap;
    }

    public HashMap<String, String> createWeatherMap() {
        HashMap<String,String> weatherMap = new HashMap<>();

        weatherMap.put("temp_c", getWeather().get("temp_c").toString());

        return weatherMap;
    }

    private JSONObject getLocation () {
        return jsonLocation;
    }

    private JSONObject getWeather () {
        return jsonWeather;
    }
}
