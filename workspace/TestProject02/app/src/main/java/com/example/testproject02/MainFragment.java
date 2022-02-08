package com.example.testproject02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;


public class MainFragment extends Fragment {
    EditText f_id, f_name, f_bt;
    userDTO dto;


    public MainFragment(userDTO dto) {
        this.dto = dto;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        f_id = rootView.findViewById(R.id.f_id);
        f_name = rootView.findViewById(R.id.f_name);
        f_bt = rootView.findViewById(R.id.f_bt);

        f_id.setText(dto.getId());
        f_name.setText(dto.getName());
        f_bt.setText(dto.getBirth());



        return rootView;
    }
}