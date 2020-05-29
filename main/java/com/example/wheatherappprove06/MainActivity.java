package com.example.wheatherappprove06;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayTemp (View view) {
        EditText editText = (EditText) findViewById(R.id.editText);

        String city = editText.getText().toString();


        GetTemp getTemp = new GetTemp(city, this);

        Thread thread1 = new Thread(getTemp, "Thread 1");
        thread1.start();

        /*String animalList[] = {"Lion","Tiger","Monkey","Elephant","Dog","Cat","Camel"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, animalList);*/
    }

    public void displayForecast (View view) {
        EditText editText = (EditText) findViewById(R.id.editText);

        String city = editText.getText().toString();

        //Context conext = get
        GetForecast getForecast = new GetForecast(city, this);

        Thread thread1 = new Thread(getForecast, "Thread 1");
        thread1.start();
    }

}
