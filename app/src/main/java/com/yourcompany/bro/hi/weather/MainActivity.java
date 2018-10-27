package com.yourcompany.bro.hi.weather;

import android.annotation.SuppressLint;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, updatedField;
    ImageView imageBackground;
    Button refresh, buttonJson;
    EditText editCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        getSupportActionBar().hide();



        cityField = findViewById(R.id.city_field);
        updatedField = findViewById(R.id.updated_field);
        detailsField = findViewById(R.id.details_field);
        currentTemperatureField = findViewById(R.id.current_temperature_field);
        humidity_field = findViewById(R.id.humidity_field);
        pressure_field = findViewById(R.id.pressure_field);
        imageBackground= findViewById(R.id.imageBackground);
        refresh= findViewById(R.id.refresh);
        editCity = findViewById(R.id.editCity);
        buttonJson= findViewById(R.id.button2);




        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Weather.placeIdTask asyncTask =new Weather.placeIdTask(new Weather.AsyncResponse() {
                    @SuppressLint("SetTextI18n")
                    public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn) {
                        cityField.setText(weather_city);
                        updatedField.setText(weather_updatedOn);
                        detailsField.setText(weather_description);
                        currentTemperatureField.setText(weather_temperature);
                        humidity_field.setText("Humidity: "+weather_humidity);
                        pressure_field.setText("Pressure: "+weather_pressure);


                        if(weather_description.contains("CLOUDS")){
                            Log.i("Detail : ",weather_description);
                            imageBackground.setBackgroundResource(R.drawable.cloud);
                        }
                        else if(weather_description.contains("RAIN")){
                            imageBackground.setBackgroundResource(R.drawable.rain);
                            Log.i("Detail : ",weather_description);

                        } else if (weather_description.contains("SNOW")){
                            imageBackground.setBackgroundResource(R.drawable.snow);
                            Log.i("Detail : ",weather_description);
                        }
                        else if (weather_description.contains("MIST")){
                            imageBackground.setBackgroundResource(R.drawable.mist);
                            Log.i("Detail : ",weather_description);
                        } else if (weather_description.contains("CLEAR")){
                            imageBackground.setBackgroundResource(R.drawable.sun);
                            Log.i("Detail : ",weather_description);
                        }  else if (weather_description.contains("THUNDERSTORM")){
                            cityField.setTextColor(Color.WHITE);
                            updatedField.setTextColor(Color.WHITE);
                            currentTemperatureField.setTextColor(Color.WHITE);
                            imageBackground.setBackgroundResource(R.drawable.strom);
                            Log.i("Detail : ",weather_description);
                        }

                    }
                });



                asyncTask.execute(String.valueOf(editCity.getText())); //  asyncTask.execute("Latitude", "Longitude")

            }
        });

        buttonJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }

        });

        }


    }

