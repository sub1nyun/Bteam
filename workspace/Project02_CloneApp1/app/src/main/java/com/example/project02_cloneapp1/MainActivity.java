package com.example.project02_cloneapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main:";
    BottomNavigationView nav_btm;
    static Fragment1 fragment1; //null 값이라 바로 붙일 수 없음
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment fragment;
    ActionBar actionBar;

    //카카오톡 하단에 있는 바텀네비게이션뷰
    //그리고 메뉴를 클릭하면 화면이 전환이 됨
    // 메뉴 전환은 -> Fragment
    // QR체크인 => Activity
    //화면이 새로 띄워지는지, 화면이 전환이 되는건지
    //바텀네비는 주로 화면은 전환하려고 사용함
    //Fragment가 붙으려면 layout이라는 영역이 필요함
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(new ListViewFragment());
        actionBar = getSupportActionBar();


       // getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
        //getSupportFragmentManager().beginTransaction() 고정 ~ 어디에 붙이느냐, 뭐를 붙이는지 후 커밋(반드시)
       // fragment1 = new Fragment1();
       // fragment2 = new Fragment2();
       // fragment3 = new Fragment3();



        // 자식 메소드 -> 새로운 기능이라 부모에서는 사용 불가
        //다운캐스팅
        nav_btm = findViewById(R.id.bottom_nav);
        //선택 됐을때 ▼
        nav_btm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item이라는 menu 객체를 넘겨줌
                //item.getItemId(); - Resources에 등록되어 있는 인트로 되어있는 ID를 확인가능
                //item.getTitle(); - Resources에 등록되어 있는 테스트(Title글씨)를 화면가능
                if(item.getItemId() == R.id.tab1) {
                  //  getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
                    fragment = new ListViewFragment2();
                    actionBar.setTitle("친구");
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(MainActivity.this, "tab1 선택 됨", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.tab2) {
                    actionBar.setTitle("채팅");
                   // getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment2).commit();
                    fragment = new ChatFragment();
                   // Toast.makeText(MainActivity.this, "tab2 선택 됨", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.tab3) {
                  //  getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                    fragment = new TabFragment();
                    actionBar.setTitle("뷰(탭레이아웃)");
                   // Toast.makeText(MainActivity.this, "tab3 선택 됨", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.tab4) {
                    actionBar.setTitle("쇼핑");
                    fragment = new GridFragment();
                   // Toast.makeText(MainActivity.this, "tab4 선택 됨", Toast.LENGTH_SHORT).show();
                }else if (item.getItemId() == R.id.tab5) {
                 //   Toast.makeText(MainActivity.this, "tab5 선택 됨", Toast.LENGTH_SHORT).show();
                    actionBar.setTitle("더보기");
                }



                //문제 ) 1.몇번 메뉴가 선택되었는지 Toast창 띄우기
                //       2. log로 찍어보기
                //       3. log와 Toast가 둘다 됐다면 디버깅모드로 확인해보기
                Toast.makeText(MainActivity.this, item.getItemId() + " ", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, R.id.tab1+ " ", Toast.LENGTH_SHORT).show();
                //id를 부여하면 자동으로 int로 id를 체번해서 R에서 사용할수있게 됨
                Log.d(TAG, "onNavigationItemSelected: 여기");
                changeFragment(fragment);
                return true;


            }
        });
    }//onCreate 밖에 만들어야함

    public void changeFragment(Fragment fragment){ //x사용 --fragment 이외의 것은 못 받음
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }


}