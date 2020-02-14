package com.example.hackridea;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class farmAccFragment extends Fragment {

    View view;

    LinearLayout calLay, exLay, dieLay, comLay, markLay, mapLay, leadLay;

    public farmAccFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.farm_acc_fragment, container, false);

        calLay = view.findViewById(R.id.calLayout);
        exLay = view.findViewById(R.id.exLayout);
        dieLay = view.findViewById(R.id.dieLayout);
        comLay = view.findViewById(R.id.commLayout);
        markLay = view.findViewById(R.id.markLayout);
        mapLay = view.findViewById(R.id.mapLayout);
        leadLay = view.findViewById(R.id.leaderLayout);

        calLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        exLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        dieLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        comLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        markLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        mapLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(getActivity(), calculator.class);
                startActivity(go);
            }
        });
        return view;
    }


}

