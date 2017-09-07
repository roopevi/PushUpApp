package com.example.rrvil.pushupapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rrvil on 07-Sep-17.
 */

public class ResultsFragment extends Fragment {

    String result;
    private OnFragmentInteractionListener mListener;

    public ResultsFragment() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_fragment, container, false);
        Button btn = (Button) view.findViewById(R.id.to_menu);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                StartScreenFragment fragment = new StartScreenFragment();

                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();
            }
        });

        TextView tv = (TextView) view.findViewById(R.id.result);

        if (getArguments() != null) {
            result = getArguments().getString("result");
        }

        int score = Integer.parseInt(result);
        Log.i("SCORE", Integer.toString(score));
        if (score < 5) {
            tv.setText(getString(R.string.poor )+ " " + getString(R.string.result) + " " + result);
        }

        else if (score > 5 && score <= 8) {
            tv.setText(getString(R.string.decent )+ " " + getString(R.string.result) + " " + result);
        }

        else if (score > 8 && score <= 15) {
            tv.setText(getString(R.string.good )+ " " + getString(R.string.result) + " " + result);
        }

        else if (score > 15) {
            tv.setText(getString(R.string.beast )+ " " + getString(R.string.result) + " " + result);
        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onDetach() {
        super.onDetach();
    }
}
