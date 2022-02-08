package com.example.project02_cloneapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

public class GridFragment extends Fragment {

    GridView gridView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_grid, container, false);
        gridView = rootView.findViewById(R.id.gridview);

        ArrayList<KakaoDTO> list = new ArrayList<>();

        for(int i = 0; i<40;i++){
            list.add(new KakaoDTO(R.drawable.shopping, "그대기억이이이"+i, "지난 사랑이 55" +i +"원"));
        }
        GridAdapter adapter = new GridAdapter(list, getContext());

        gridView.setAdapter(adapter);
        // listViewAdapter <= BaseAdapter 상속받음
        // GridView에서도 사용이 가능함. (어느 부분을 수정하면 사용이 가능할까?)
        // ArrayList<KakjaoDTO> 만들기 아이템 갯수는 40
        // gridView <=> Adapter연결하기
        return rootView;
    }
}