package com.example.rrvil.pushupapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by rrvil on 07-Sep-17.
 */

public class StartScreenFragment extends Fragment {

    public StartScreenFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_screen_fragment, container, false);

        Button go = (Button) view.findViewById(R.id.go_btn);

        go.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                PushUpCounterFragment fragment = new PushUpCounterFragment();

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment).addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
