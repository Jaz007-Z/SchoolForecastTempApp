package com.example.wheatherappprove06;

import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class WeatherForecast {
    @SerializedName("list")
    List<WeatherForecastItem> items = new ArrayList<WeatherForecastItem>();
    Max max = new Max();


    public WeatherForecast getForecast(String city) {
        WeatherForecast weatherForecast = new WeatherForecast();
        try {
            //strings for URL, uses input for city
            String urlP1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
            String urlP2 = city;
            String urlP3 = "&units=imperial&apiKey=141cebac288af103eb6effc92021a7e4";

            //connects to URL
            URL myURL = new URL(urlP1 + urlP2 + urlP3);
            URLConnection myURLConnection = myURL.openConnection();
            myURLConnection.connect();

            //obtains JSON from URL
            BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String inputLine;
            inputLine = in.readLine();

            //deserializes JSON into weatherConditions object
            Gson gson = new Gson();
            weatherForecast = gson.fromJson(inputLine, WeatherForecast.class);

            in.close();
        } catch (MalformedURLException e) {
            // Output expected MalformedURLExceptions.
            e.printStackTrace();
        } catch (IOException ex) {
            // openConnection() failed
            ex.printStackTrace();
        }

        //obtain max wind/temp in forecast before returning
        weatherForecast.findMaxWind();
        weatherForecast.findMaxTemp();



        return weatherForecast;
    }

   public  List<WeatherForecastItem> getItems() {
        return this.items;
   }

    public  List<WeatherForecastItem> get() {
        return this.items;
    }


    public void display() {
        for (WeatherForecastItem item : items) {
            item.display();
        }
    }


    //finds max temperature in forecast
    public void findMaxTemp() {
        float highest = -10000;
        for (WeatherForecastItem item : items) {
            float temp = item.getHighestTemp();
            if (temp > highest) {
                highest = temp;
            }
        }
        max.maxTemp = highest;
    }

    //finds max wind in forecast
    public void findMaxWind() {
        float highest = -10000;
        for (WeatherForecastItem item : items) {
            float wind = item.getHighestWind();
            if (wind > highest) {
                highest = wind;
            }
        }
        max.maxWind = highest;
    }

    public float getMaxTemp() {
        return max.maxTemp;
    }

    public float getMaxWind() {
        return max.maxWind;
    }



}
