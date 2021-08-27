package weatherapp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class HTTPGetRequest {
    String location;
    String weatherInfo;

    /**
     * Empty Constructor.
     */
    public HTTPGetRequest() {
        location = null;
        weatherInfo = null;
        sendGet();
    }

    /**
     * Constructor for HTTPGetRequest class.
     * @param location (String) location to retrieve weather info from
     */
    public HTTPGetRequest(String location) {
        this.location = location;
        weatherInfo = null;
        sendGet();
    }

    /**
     * Sends the get request with location to the weather api.
     */
    private void sendGet() {
        URL url = null;
        HttpURLConnection con = null;
        Scanner sc = null;
        StringBuilder jsonFormat = new StringBuilder();

        String strUrl = "http://api.weatherapi.com/v1/current.json?key=" + ApiKey.apiKey + "&q=" + location;

        try{
            url = new URL(strUrl); //Create URL object using the string url
        } catch(MalformedURLException e) {
            e.printStackTrace();
        }

        try{
            con = (HttpURLConnection)url.openConnection();//typecast url object into httpurlconnection object
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            con.setRequestMethod("GET"); //Set request type
        } catch (ProtocolException e) {
            e.printStackTrace();
        }

        try{
            con.connect(); //connect to api
            sc = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(sc.hasNext()) {
            jsonFormat.append(sc.nextLine());
        }

        sc.close();
        con.disconnect();

        weatherInfo = jsonFormat.toString();
//        try{
//            BufferedWriter writer = new BufferedWriter(new FileWriter(".\\test.json"));
//            writer.write(weatherInfo);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }

    /**
     * Sets location string.
     * @param location (String) location to retrieve weather info from
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns location string.
     * @return location (String)
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns weatherInfo string.
     * @return weatherInfo (string)
     */
    public String getWeatherInfo() {
        return weatherInfo;
    }
}
