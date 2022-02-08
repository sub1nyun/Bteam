package com.example.project02_cloneapp1;

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


public class ListViewFragment extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_view, container, false);
        //return에 있던 값을 담아줌 ▲
        // * 액티비티는 find, Fragment 붙여놓은 rootView에서 find로 찾음.
        //ex) 친구 목록 400개 묶지 안고 보내면 -> 400번 반복해야함
        //list 사용하게 되면 어뎁터가 뿌려줌
        // 자바 Data를 ListView에 넣기 위한 객체(Adapter)
        // 기본적으로 제공해주는 어뎁터는 xml을 따로가지고있음 (TextView 하나 들어있는 형태부터 ~여러가지)
        // 대부분 사용할때는 커스터마이징을함 (이미지뷰 여러개, 텍스트뷰 여러개, 버튼... 등등)
        // 앞으로 어플리케이션(App)을 볼때 일관된 모양으로 반복이 되는 구간은(레이아웃) 90%이상
        //ListView 또는 RecyleView같은 위젯을 묶어놓은 목록형태의 위젯이다
        listView =rootView.findViewById(R.id.listview);
        //기본적인 형태의 어뎁터 ArrayAdapter (사용 빈도는 낮음) String 값만 텍스트뷰로 보여주기때문에
        //디자인 적으로는 별로 예쁘지 않음
        //어뎁터에 세팅되는 데이터 타입은 List구조 또는 index를 가진구조여야만한다.
        //ex) 친구가 10명 -> 10번 반복 100명 -> 100번 반복
        ArrayList<String> list = new ArrayList<>();
        for(int i=1; i<=30; i++) {
            list.add("데이터"+(i));
        }

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        //listView안에 Item 클릭시 알기
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //getContext()를 이용해서 Main꺼 가져옴
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parent.getItemIdAtPosition(position)+"", Toast.LENGTH_SHORT).show();
            }
        });
        //ListView <- 세로만 가능 빈도 낮음
        //RycycleView <- 가로 세로 지그재그(x) GridView <- 격자
        return rootView;
        //inflate 하는 곳을 찾음


    }


}