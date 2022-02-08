package com.example.project02_cloneapp1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;


public class TabFragment extends Fragment {
    TabLayout tabLayout;
    FragmentManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab, container, false);
       tabLayout = rootView.findViewById(R.id.tabs);
       tabLayout.addTab(tabLayout.newTab().setText("My 뷰"));
        tabLayout.addTab(tabLayout.newTab().setText("발견"));
        tabLayout.addTab(tabLayout.newTab().setText("잔여백식").setId(2));
        tabLayout.addTab(tabLayout.newTab().setText("코로나19"));
        tabLayout.addTab(tabLayout.newTab().setText("카카오TV"));




        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //tab객체를 이용해서 몇번째 탭이 선택되었는지 logd찍어보기
                 if(tab.getPosition() == 0) {
                     Toast.makeText(getContext(), "My 뷰", Toast.LENGTH_SHORT).show();
                 }else if(tab.getText().equals("발견")){
                     Toast.makeText(getContext(), "발견", Toast.LENGTH_SHORT).show();
                 }else if(tab.getId() == 2) {
                     Toast.makeText(getContext(), "코로나", Toast.LENGTH_SHORT).show();
                 }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
        //getActivty null 어플종료됨
//        public void changeFrag(Fragment fragment){ //x사용 --fragment 이외의 것은 못 받음
 //           getActivity().getSupportFragmentManager().beginTransaction()
  //                  .replace(R.id.child_container, fragment).commit();


    }
}