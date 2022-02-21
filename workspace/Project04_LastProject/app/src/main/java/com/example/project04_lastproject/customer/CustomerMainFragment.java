package com.example.project04_lastproject.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


public class CustomerMainFragment extends Fragment {

    RecyclerView rcv_cus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_customer_main, container, false);
        //▲ inflate 된 화면에서 위젯을 찾기위한 처리 -> rootView에서 찾기위해 묶어둠
        //item 한 칸마다 들어감 아이템 생성
        rcv_cus = rootView.findViewById(R.id.rcv_cus);
        refresh(inflater);
        return rootView;
    }

    public void refresh(LayoutInflater inflater) {
        AskTask task = new AskTask("list.cu");

        InputStream in = CommonMethod.executeGet(task);
        Gson gson = new Gson();
        List<CustomerVO> list = gson.fromJson(new InputStreamReader(in), new TypeToken<List<CustomerVO>>() {
        }.getType());
        //리사이클러뷰는 어댑터 외에도 한가지 필요한게 있음 (LayoutManger 아이템을 어떤 방향으로 보여줄지를 결정)
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        CustomerAdapter adapter = new CustomerAdapter(inflater, getContext(), list, this); // <= 오류가 난다변 (null) Context 자체를 받아오면 됨
        rcv_cus.setLayoutManager(manager);
        rcv_cus.setAdapter(adapter);
    }
}

