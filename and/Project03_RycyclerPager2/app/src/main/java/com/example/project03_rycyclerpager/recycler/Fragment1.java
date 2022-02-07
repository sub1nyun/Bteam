package com.example.project03_rycyclerpager.recycler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project03_rycyclerpager.R;

import java.util.ArrayList;


public class Fragment1 extends Fragment {
        ArrayList<RecDTO> list;
        RecyclerView frg_img;


    public Fragment1(ArrayList<RecDTO> list) {
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        frg_img = rootView.findViewById(R.id.frg_img);
        RecAdapter adapter = new RecAdapter(list, inflater);
        frg_img.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
        frg_img.setLayoutManager(manager);




//        frg_img = rootView.findViewById(R.id.frg_img);
//        RecAdapter adapter = new RecAdapter(list, inflater);
//        frg_img.setAdapter(adapter);
//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, true);
//        frg_img.setLayoutManager(manager);




        return rootView;
    }
}