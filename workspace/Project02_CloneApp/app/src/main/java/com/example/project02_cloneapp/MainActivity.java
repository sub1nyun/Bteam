package com.example.project02_cloneapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.project02_cloneapp.recyclerview.RecyclerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main: ";
    BottomNavigationView nav_btm;
    Fragment fragment; // null
    ActionBar actionBar;

    public void changeFragment( Fragment to_frag ){
        getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, to_frag).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragment(new ListViewFragment2());
        //changeFragment(new ListViewFragment());
        //      getSupportFragmentManager().beginTransaction()
        //                .replace(R.id.container, new ListViewFragment()).commit();

        actionBar = getSupportActionBar();
        actionBar.setTitle("친구 목록");
        TestClass tc = new TestClass();
        tc.showToast(MainActivity.this , "TestClass호출 메인액티비티");

        nav_btm = findViewById(R.id.bottom_nav);
        nav_btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // item.getItemId() // ? Resources에 등록되어 있는 인트로 되어있는 id를 확인가능
                // item.getTitle() // ? Resources에 등록되어있는 텍스트 ( title 글씨) 를 확인가능
                if(R.id.tab1 == item.getItemId()){
                    Toast.makeText(MainActivity.this, "tab1 선택됨", Toast.LENGTH_SHORT).show();
                    // fragment = new Fragment1(MainActivity.this);
                    actionBar.setTitle("친구 목록");
                    fragment = new ListViewFragment2();
                } else if(R.id.tab2 == item.getItemId()){
                    Toast.makeText(MainActivity.this, "tab2 선택됨", Toast.LENGTH_SHORT).show();
                    // fragment = new Fragment2();
                    actionBar.setTitle("채팅");
                    fragment = new ChatFragment();
                } else if(R.id.tab3 == item.getItemId()){
                    Toast.makeText(MainActivity.this, "tab3 선택됨", Toast.LENGTH_SHORT).show();
                    actionBar.setTitle("뷰(탭레이아웃)");
                    fragment = new TabFragment(MainActivity.this);
                } else if(R.id.tab4 == item.getItemId()){
                    Toast.makeText(MainActivity.this, "tab4 선택됨", Toast.LENGTH_SHORT).show();
                    actionBar.setTitle("쇼핑");
                    fragment = new GridFragment();
                } else if(R.id.tab5 == item.getItemId()){
                    Toast.makeText(MainActivity.this, "tab5 선택됨", Toast.LENGTH_SHORT).show();
                    actionBar.setTitle("뷰(리사이클러뷰)");
                    fragment = new RecyclerFragment();
                }
                changeFragment(fragment);

                /*Toast.makeText(MainActivity.this, item.getTitle() + "번 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, R.id.tab1 + "아이디의 메뉴 선택(R.id).", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, item.getItemId() + "아이디의 메뉴 선택(item.getitemid)", Toast.LENGTH_SHORT).show();*/
                // 문제 1. 몇번 메뉴가 선택되었는지 Toast창으로 띄우기
                //      2. log로 찍어보기
                //      3. 로그와 Toast가 둘 다 됐다면 디버깅모드로도 확인해보기.

                Log.d(TAG, "onNavigationItemSelected: " + item.getTitle() + "번 메뉴가 선택되었습니다.");
                return true;
            }
        });
    }
}