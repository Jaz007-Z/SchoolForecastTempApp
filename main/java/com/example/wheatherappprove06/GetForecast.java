package com.example.wheatherappprove06;

import android.app.Activity;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class GetForecast implements Runnable {

    String city;
    private WeakReference<Activity> activityRef;

    public GetForecast(String city, Activity activity) {
        this.city = city;
        this.activityRef = new WeakReference<Activity>(activity);
    }



    @Override
    public void run() {
        System.out.println("thread called!");
        WeatherForecast weatherForecast = new WeatherForecast();
        weatherForecast = weatherForecast.getForecast(city);
        weatherForecast.display();

        final Activity activity = activityRef.get();

        String animalList[] = {"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};
        List<WeatherForecastItem> items = new ArrayList<WeatherForecastItem>();
        items = weatherForecast.getItems();
        List<String> forecastItems = new ArrayList<String>();
        for (WeatherForecastItem weatherForecastItem : items) {
            forecastItems.add(weatherForecastItem.convertString());
        }

        /*Intent intent=new Intent(Main.this,ActivityTo.class);
        intent.putExtra("StringKey", animalList);
        startActivity(intent);*/

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity.getBaseContext(),
                        android.R.layout.simple_list_item_1, forecastItems);

        if (activity != null) {
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    // This is the code that will run on the UI thread.
                    ListView listView = (ListView) activity.findViewById(R.id.listView);
                    listView.setAdapter(adapter);


                    Toast toast = Toast.makeText(activity, "Forecast Retrieved ", Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

    }


}
