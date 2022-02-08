package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Fragment4 extends Fragment {

    TextView test;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        test.findViewById(R.id.test);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_4, container, false);
        return rootView;


    }
}