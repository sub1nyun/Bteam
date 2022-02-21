package com.example.project04_lastproject.notice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.example.project04_lastproject.employees.EmployeeAdapter;
import com.example.project04_lastproject.employees.EmployeeVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class NoticeFragment extends Fragment {

    ExpandableListView noti_expd;
    ArrayList<GroupDTO> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.fragment_notice, container, false);
        noti_expd = itemView.findViewById(R.id.noti_expd);
        //리스트 먼저 만들기
        for (int i =0; i<10; i++) {
            //자식 개수가 필요함
            //동적으로 부모의 갯수와 자식의 수가 다르다면 이런식으로 만들어야함
            //어뎁터 사용방식이 약간 다름 -> 부모를 클릭했을때 자식 요소가 보여져야하니 xml이 2개가 필요함
            ArrayList<SubDTO> listSub = new ArrayList<>();
            for(int j =0; j<i; j++) {
                listSub.add(new SubDTO("자식 글 제목", "자식 글 내용"));
            }
            list.add(new GroupDTO("글 제목1"+i, "글 내용"+i, listSub));
        }
        ExpdAdapter adapter = new ExpdAdapter(list,inflater);
        noti_expd.setAdapter(adapter);



        return  itemView;
    }
}