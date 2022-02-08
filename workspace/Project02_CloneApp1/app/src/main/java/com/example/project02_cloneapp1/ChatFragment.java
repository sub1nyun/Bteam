package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;


public class ChatFragment extends Fragment {
    ArrayList<KaKaoDTO> list = new ArrayList<>();
    //ChatFragment  Class 생성
    //xml 디자인 복붙
    //MainActivity 에서 2번 클릭시 fragment를 new ChatFragment();로 변경
    //xml에서 ListView를 찾아줌 -> listView랑 연결 rootView에서
    //list를 찾고 나서는 어뎁터가 필요함
    //어뎁터 클래스 생성 -> 상속받아야 사용가능 (일반 클래스는 어뎁터 x)
    //xml에 list_item 복붙해서 디자인 추가
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat, container, false);

        ListView listView = rootView.findViewById(R.id.listview);
        //어뎁터 먼저 생성 - list가 들어가야함 -> 선언
        //UUID uuid = new UUID(); -> 고유식별자
        //인터넷에서 고유 식별자로 사용하는 데이터
        //100%로 중복을 방지하는건 아님 - 년원일시분초+UUID
        //UUID random = UUID.randomUUID(); //os에서 가져온 초기화식 new X
        for(int i=0; i<30; i++) {
            UUID random = UUID.randomUUID();
            list.add(new KaKaoDTO(R.drawable.music, random.toString(), "msg"+i, "15:0" +i));
        }//getLayoutInflater() Activi받아오는 메소드 26번의 inflaster
        ChatAdapter adapter = new ChatAdapter(list,inflater);
        //list뷰에 어뎁터 연결
        listView.setAdapter(adapter);
        return rootView;
    }
}