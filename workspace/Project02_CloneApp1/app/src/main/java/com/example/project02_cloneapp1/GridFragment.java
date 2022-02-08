package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;


public class GridFragment extends Fragment {
    //그리드뷰 찾는것부터 시작
    GridView gridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_grid, container, false);
       gridView = rootView.findViewById(R.id.gridView);
        ArrayList<KaKaoDTO> list = new ArrayList<>();
        for(int i =0; i< 50; i++) {
            list.add(new KaKaoDTO(R.drawable.shopping, "name" +i, "msg" +i, "123"));
        }

        GridAdapter adapter = new GridAdapter(list, inflater);
        gridView.setAdapter(adapter);

        //listViewAdapter <= BaseAdpater 상속받음
        // GridView에서도 사용이 가능함 ( 어느 부분을 수정하면 가능할지)
        // ArrayList<KaKaoDTO> 만들기 아이템 갯수 40
        // gridView <=> Adapter 연결하기


        return  rootView;
    }
}