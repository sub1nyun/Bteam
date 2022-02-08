package com.example.project02_cloneapp.recyclerview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project02_cloneapp.R;

import java.util.ArrayList;


public class RecyclerFragment extends Fragment {
    RecyclerView recviewHo;
    RecyclerView recviewVe;
    ArrayList<RecDTO> list = new ArrayList<>();
    //DTO 구조를 묶어놓은 것으로 list를 먼저 준비
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_recycler, container, false);
        recviewHo = rootView.findViewById(R.id.recview_ho);
        recviewVe = rootView.findViewById(R.id.recview_ve);
        for(int i=0; i<50; i++) {
            list.add(new RecDTO(R.drawable.ic_launcher_background, "텍스트뷰 글씨" + (i)));
        }//<- DB와 연동해서 실제 데이터를 가지고오면 됨
        //DTO또한 DB컬럼와 매칭이 되면 됨됨
        //어뎁터를 만들기전에 1.리사이클러뷰를 붙일 레이아웃(java)를 준비 (Activivy , Fragment)
        //2. 한 칸마다 보여질 데이터 타입(DTO), 보여질 뷰를 준비(rec_item)
        //3. Adapter를 만들기
        //4. Adatper <==> RecyclerView랑 연결 setAdapter
        //액티에서 할때는 만들어서 보내줌 ▼
        //LayoutInflater inflater1 = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //24번에 inflater가 들어있어서 보내주면 됨 ▼
        RecAdapter adapter = new RecAdapter(list, inflater);
        recviewHo.setAdapter(adapter);
        recviewVe.setAdapter(adapter);
        //5.LayoutManager <-라는것을 사용하여 가로, 세로, 지그재그 인지를 지정해줘야함
        //(ListView,GridView)
        //1. context , 보여줄거 , 반대로 보여줄 것 ~
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        LinearLayoutManager manager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recviewHo.setLayoutManager(manager);
        recviewVe.setLayoutManager(manager2);
        //목록 형태를 가진 데이터는 우리가 원하는 모양으로 데이터를 보여주기 위해서 반드시 xml <- Adapter
        return rootView;
    }
}