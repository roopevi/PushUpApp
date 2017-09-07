package com.example.rrvil.pushupapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by rrvil on 07-Sep-17.
 */

public class PushUpCounterFragment extends Fragment {

    private SensorManager sm;
    private TextView tv, timer;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;
    private int counter = 0;
    int result = 0;
    private OnFragmentInteractionListener mListener;

    public PushUpCounterFragment () {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.push_up_fragment, container, false);

        sm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        tv = (TextView) view.findViewById(R.id.text_view);
        timer = (TextView) view.findViewById(R.id.timer);

        if (sm.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            proximitySensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }else {

        }

        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("DONE!");

                ResultsFragment rf = new ResultsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("result", Integer.toString(result));
                mListener.sendResult(bundle);

                Log.i("DEBUG: ", bundle.toString());
                mListener.sendResult(bundle);

            }
        }.start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SensorEventListener proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()) {
                    // Detected something nearby
                    counter++;
                    tv.setText(Integer.toString(counter));

                    result = counter;

                } else {

                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sm.registerListener(proximitySensorListener,
                proximitySensor, 1 * 1000 * 1000);
    }

    @Override
    public void onPause() {
        super.onPause();

        sm.unregisterListener(proximitySensorListener);
        Log.i("DEBUG: ", "proximity sensor closed");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
