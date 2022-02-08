package com.example.project02_cloneapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewFragment2 extends Fragment {

    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_list_view, container, false);
        lv = rootView.findViewById(R.id.listview);
        ArrayList<KakaoDTO> list = new ArrayList<>();
        for(int i = 0; i< 30; i++){
            if(i%2==0){
                list.add(new KakaoDTO(R.drawable.gear, "이름 " + i, "상태메시지 " + i));
            } else {
                list.add(new KakaoDTO(R.drawable.eye, "이름 " + i, "상태메시지 " + i));
            }
        }
        KakaoAdapter adapter = new KakaoAdapter(list, getContext());
        lv.setAdapter(adapter);

        return rootView;
    }
}