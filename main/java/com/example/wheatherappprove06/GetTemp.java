package com.example.wheatherappprove06;

import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class GetTemp implements Runnable {

    String city;
    private WeakReference<Activity> activityRef;

    public GetTemp(String city, Activity activity) {
        this.city = city;
        this.activityRef = new WeakReference<Activity>(activity);
    }



    @Override
    public void run() {
        System.out.println("thread called!");
        WeatherConditions weatherConditions = new WeatherConditions();
        final float temp = weatherConditions.getWeather(city);
        System.out.println("Temp is " + temp);
        final Activity activity = activityRef.get();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // This is the code that will run on the UI thread.
                    Toast toast = Toast.makeText(activity, "Temperature: " + temp, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

    }


}
