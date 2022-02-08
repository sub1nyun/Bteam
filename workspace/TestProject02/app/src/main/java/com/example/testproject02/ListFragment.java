package com.example.testproject02;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ListFragment extends Fragment {
    ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list, container, false);
        listView = rootView.findViewById(R.id.listview);
        ArrayList<ListDTO> list = new ArrayList<>();
        for(int i=1; i<=31; i++) {
            if(i%2 ==1) {
                list.add(new ListDTO(R.drawable.squirrel3,R.drawable.squirrel2,"너부리야x"+i,"때릴거야?x"+i));
            }else if(i%2 ==0) {
                list.add(new ListDTO(R.drawable.buri1,R.drawable.buri2,"네?x"+i,"열받네?x"+i));
            }

        }
        ListAdapter adapter = new ListAdapter(list, getContext());
        listView.setAdapter(adapter);

        return rootView;
    }
}