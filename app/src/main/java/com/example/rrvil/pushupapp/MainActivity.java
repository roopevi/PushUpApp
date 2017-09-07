package com.example.rrvil.pushupapp;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private SensorManager sm;
    private TextView tv;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            StartScreenFragment startScreenFragment = new StartScreenFragment();

            startScreenFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, startScreenFragment).commit();
        }
    }

    public void sendResult(Bundle bundle) {
        ResultsFragment resultsFragment = new ResultsFragment();
        resultsFragment.setArguments(bundle);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, resultsFragment);
        transaction.commit();
    }
}
