package com.example.project04_lastproject.employees;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class EmployeeFragment extends Fragment {

    RecyclerView emp_rcv;
    Gson gson = new Gson();
    SwipeRefreshLayout swipe;
    SearchView searchView; //안드x에 있음
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup itemView = (ViewGroup) inflater.inflate(R.layout.fragment_employee, container, false);

        emp_rcv= itemView.findViewById(R.id.emp_rcv);
        swipe = itemView.findViewById(R.id.emp_swipe);
        searchView = itemView.findViewById(R.id.emp_srchview);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { //검색 버튼을 눌렀을때까지의 텍스트를 그대로 가지고옴옴
                Toast.makeText(getContext(), "서치뷰 : ", Toast.LENGTH_SHORT).show();
                //String query <= 값이용해서 employees에 where조건에 사용해보기
               search_employee(inflater, query);


               return true;
            }


            @Override
            public boolean onQueryTextChange(String query) {
                //텍스트가 바뀔때마다 바뀌는 텍스트를 그대로 가지고옴
                //사용하려면 리턴을 true로 바꿔줘야함
                search_employee(inflater, query);
                return true;
            }
        });

        //new로 인터페이스 넘겨줌
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refresh가 스와이프 되서 동작하는 상태가 되면 처리할 이벤트
                Toast.makeText(getContext(), "새로 고침 완료", Toast.LENGTH_SHORT).show();
                //반드시 써줘야 하는것
                swipe.setRefreshing(false); //새로고침 화살표를 없에는 처리

            }
        });


        //1.어싱크 이용해서 List(목록데이터 먼저 가져오기)
        //Adapter , emp_item.xml
        search_employee(inflater);

        return itemView;
    }
    public void search_employee(LayoutInflater inflater) {

        AskTask task = new AskTask("list.hr");
        InputStream in = CommonMethod.executeGet(task);
        List<EmployeeVO> list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<EmployeeVO>>(){}.getType());
        String data = "";

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        EmployeeAdapter adapter = new EmployeeAdapter(list, inflater, getContext());
        emp_rcv.setLayoutManager(manager);
        emp_rcv.setAdapter(adapter);

    }
    public void search_employee(LayoutInflater inflater, String search) {
        AskTask task = new AskTask("list.hr");
        task.addParam("search", search);
        InputStream in = CommonMethod.executeGet(task);
        List<EmployeeVO> list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<EmployeeVO>>(){}.getType());
        String data = "";

        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        EmployeeAdapter adapter = new EmployeeAdapter(list, inflater, getContext());
        emp_rcv.setLayoutManager(manager);
        emp_rcv.setAdapter(adapter);

    }
}