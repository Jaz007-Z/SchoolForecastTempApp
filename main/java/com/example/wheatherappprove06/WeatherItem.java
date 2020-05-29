package com.example.wheatherappprove06;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import java.util.*;
import java.net.*;

public class WeatherItem {
    String main;
    String description;

    public void display() {
        System.out.println("Weather description: " + main + ": " + description);
    }
}
