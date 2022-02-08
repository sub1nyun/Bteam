package com.example.project02_cloneapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class TabFragment extends Fragment {

    TabLayout tabLayout;
    private static final String TAG = "TabFragment";
    MainActivity context;

    public TabFragment(Activity context) {
        this.context = (MainActivity) context;
    }

    public void changeFragment( Fragment to_frag ){
        context.getSupportFragmentManager().beginTransaction()
                .replace(R.id.child_container, to_frag).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_tab, container, false);
        tabLayout = rootView.findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("My뷰"));
        tabLayout.addTab(tabLayout.newTab().setText("발견"));
        tabLayout.addTab(tabLayout.newTab().setText("코로나19").setId(2));
        tabLayout.addTab(tabLayout.newTab().setText("잔여백신"));
        tabLayout.addTab(tabLayout.newTab().setText("카카오TV"));
        changeFragment(new Fragment2());

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // tab객체를 이용하여 몇번째 탭이 선택되었는지 log 찍어보기

                Log.d(TAG, "onTabSelected: " + tab.getPosition());
//                String add = "";
//                tab.getPosition()?
//                tab.getText()?
//                tab.getTag()?

                if(tab.getPosition() == 0){
                    Toast.makeText(getContext(), "My뷰", Toast.LENGTH_SHORT).show();
                    changeFragment(new Fragment2());
                }else if(tab.getText().equals("발견")){
                    Toast.makeText(getContext(), "발견", Toast.LENGTH_SHORT).show();
                    changeFragment(new Fragment3());
                } else if(tab.getId() == 2){
                    Toast.makeText(getContext(), "코로나19", Toast.LENGTH_SHORT).show();
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
    }
}