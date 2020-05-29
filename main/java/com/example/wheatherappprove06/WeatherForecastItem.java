package com.example.wheatherappprove06;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.util.*;
import java.net.*;

public class WeatherForecastItem {
    @SerializedName("main")
    Map<String, Float> measurements;
    List<WeatherItem> weather;

    @SerializedName("wind")
    Map<String, Float> wind;
    @SerializedName("dt_txt")
    String time;



    public WeatherForecastItem() {
        measurements = new HashMap<>();
        wind = new HashMap();
    }

    public void display() {
        System.out.println("Weather info:");
        System.out.println("Time: " + time);
        System.out.println("measurements: " + measurements);
        displayWeather();
        System.out.println("Wind: " + wind);

    }

    public String convertString() {
        String string = time + " " + measurements + " " + wind + " " + weather + "\n" +
                "";
        return string;
    }

    public void displayWeather(){
        for (WeatherItem weatherItem : weather) {
            weatherItem.display();
        }
    }

    public float getHighestTemp(){
        return measurements.get("temp_max");
    }

    public float getHighestWind(){
        return wind.get("speed");
    }


}

