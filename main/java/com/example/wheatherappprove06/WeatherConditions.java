package com.example.wheatherappprove06;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.util.*;
import java.net.*;

public class WeatherConditions {
    int id;
    String name;
    @SerializedName("main")
    Map <String, Float> measurements;

    public WeatherConditions() {
        id = 0;
        name = null;
        measurements = new HashMap<>();
    }

    //gets temp
    public float getWeather(String city) {
        WeatherConditions weatherConditions = new WeatherConditions();
        try {
            //strings for URL, uses input for city
            String urlP1 = "https://api.openweathermap.org/data/2.5/weather?q=";
            String urlP2 = city;
            String urlP3 = "&units=imperial&apiKey=141cebac288af103eb6effc92021a7e4";

            //connects to URL
            URL myURL = new URL(urlP1 + urlP2 + urlP3);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();

            //obtains JSON from URL
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine = in.readLine();
            in.close();

            //deserializes JSON into weatherConditions object
            Gson gson = new Gson();
            weatherConditions = gson.fromJson(inputLine, WeatherConditions.class);

        } catch (MalformedURLException e) {
            // Output expected MalformedURLExceptions.
            e.printStackTrace();
        } catch (IOException ex) {
            // openConnection() failed
            ex.printStackTrace();
        }
        return weatherConditions.measurements.get("temp");
    }


    public WeatherConditions getForecast(String city) {
        WeatherConditions weatherConditions = new WeatherConditions();
        try {
            String urlP1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
            String urlP2 = city;
            String urlP3 = "&units=imperial&apiKey=141cebac288af103eb6effc92021a7e4";

            URL myURL = new URL(urlP1 + urlP2 + urlP3);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            weatherConditions = weatherConditions.decode(inputLine);

        } catch (MalformedURLException e) {
            // Output expected MalformedURLExceptions.
            e.printStackTrace();
        } catch (IOException ex) {
            // openConnection() failed
            ex.printStackTrace();
        }


        return weatherConditions;
    }

    public WeatherConditions decode(String json) {
        Gson gson = new Gson();
        WeatherConditions weatherConditions = new WeatherConditions();
        weatherConditions = gson.fromJson(json, WeatherConditions.class);
        return weatherConditions;
    }

    public void display() {
        System.out.println("Weather info:");
        System.out.println("id: " + id);
        System.out.println("name: " + name);
        System.out.println("measurements: " + measurements);
    }


}