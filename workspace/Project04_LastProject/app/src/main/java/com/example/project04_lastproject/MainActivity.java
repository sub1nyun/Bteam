package com.example.project04_lastproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonVal;
import com.example.project04_lastproject.customer.CustomerMainFragment;
import com.example.project04_lastproject.employees.EmployeeFragment;
import com.example.project04_lastproject.notice.NoticeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView tv1;
    BottomNavigationView btm_nav;

    Toolbar toolbar ;
    NavigationView nav_main;
    DrawerLayout drawer_layout;

    int fragIndex = 0;
//    Fragment nowfragment = new CustomerMainFragment();
//    Fragment backfragment = new EmployeeFragment();
    Fragment nowFragment;
    //페이지를 기억하고 있어야함
    Fragment backFragment;
    //SWAP을 이용하면 이전과 현재 프래그먼트를 저장해놓고 동적으로 사용하는것이 가능함
    //addBackStackTrace등 제공하는 메소드 <= ( 될때가 있고 안 될때가 있음, 자바코드로 직접 작성을 하면 디버깅과
    //안정성이 더 좋음 )
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN); //시계 영역을 없에는 처리
        //getHashKey(); API를 사용하다가 필요하면 호출해서 사용하기

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); //커스텀한 툴바로 액션바 바꿔치기
        drawer_layout = findViewById(R.id.drawer_layout);
        nav_main =findViewById(R.id.nav_main);
        //햄버거 버튼 (토글) 만들기
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close
        );
        //드로우 레이아웃에 추가해주기
        drawer_layout.addDrawerListener(toggle); //액션바와 drawer_layout의 관계를 만듬
        toggle.syncState(); //상태를 공유
        //사진을 넣기 위해서는 headerLayout을 빼와야함 -> 여러게 넣을 수는 있음 (대부분 하나)

        //메소드 이용해서 빼옴
        View nav_headerview = nav_main.getHeaderView(0);
        ImageView imgv_profile = nav_headerview.findViewById(R.id.imgv_profile);
        TextView tv_logid = nav_headerview.findViewById(R.id.tv_logid);

        Glide.with(MainActivity.this).load(CommonVal.loginInfo.getImg_path()).into(imgv_profile);
        tv_logid.setText(CommonVal.loginInfo.getId()+"님 환영합니다");





        btm_nav= findViewById(R.id.btm_nav);
        //네트워크 오류 잡기
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nowFragment = new CustomerMainFragment();
        changefragment(nowFragment);
        toolbar.setTitle("고객 관리");


    //여러개의 위젯을 사용할때 핸들링
        nav_main.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //아이템을 넘겨줌
                if(item.getItemId() == R.id.nav_menu_cus) {
                    //강제로 바텀 내비게이션 클릭 효과
                    btm_nav.setSelectedItemId(R.id.muenu_cus);

                }else if(item.getItemId() == R.id.nav_menu_emp) {
                    btm_nav.setSelectedItemId(R.id.muenu_emp);

                }else if(item.getItemId() == R.id.nav_menu_notice) {
                    btm_nav.setSelectedItemId(R.id.muenu_notice);
                }
                //네비게이션 닫는 처리
                drawer_layout.closeDrawer(GravityCompat.START);
                return true; //선택이 될 수 있게 true
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CustomerMainFragment()).commit();
        //editText = findViewById(R.id.edt_mapping);
        //tv1 = findViewById(R.id.tv1);

               // AskTask task = new AskTask(editText.getText()+".te");
              //  task.execute();
                //그냥 생성해서 실행하면 됨


        //메소드를 사용하다 보면 중단에 줄이 있는 경우는 상위버전에서 삭제 또는 수정이 생길 수 있다
        //라는 것을 안드로이드 스튜디오에서 알려주고있는것
    btm_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(! nowFragment.getClass().isInstance(backFragment)) {   //처음에는 홈이 담김
                backFragment = nowFragment; //두개의 프래그먼트가 다르다면
            }
            if(item.getItemId() == R.id.muenu_cus ) {
                nowFragment = new CustomerMainFragment();
                toolbar.setTitle("고객 관리");
            }else if(item.getItemId() == R.id.muenu_emp) {
             nowFragment = new EmployeeFragment();
                toolbar.setTitle("사원 관리");

            }else if(item.getItemId() == R.id.muenu_notice)
                nowFragment = new NoticeFragment();
                toolbar.setTitle("공지 사항");
            changefragment(nowFragment);
            return true;
        }
    });

    //블로그에서 복사함
    }
    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    //뒤로 가기했을때 이전 플래그먼트를 보여주는 처리
    //back버튼을 눌렀을때
    //변수가 한가지 필요함
    private long backTime = 0; //뒤로가기를 눌렀을때 저장해놓을 변수(시간)
    @Override
    public void onBackPressed() {

        //뒤로가기 버튼을 눌렀음을 감지
        if(System.currentTimeMillis() > backTime+ 2000) { //시간을 재는것  2000 = 2초 - 시간 저장
            backTime = System.currentTimeMillis(); //뒤로가기를 누른시점에서 시간값을 저장
            Toast.makeText(MainActivity.this, "뒤로가기 한번 더 누르면 처리할 예정", Toast.LENGTH_SHORT).show();

            return; //밑의 블럭 실행 방지
        }

        if(System.currentTimeMillis() <=  backTime +2000) {    //실제 뒤로가기 처리
         //Class의 비교 ? 어떻게 해야하는가 instence of 라는 비교문을 사용해야함
            // isInstance 같은 타입의 객체를 사용해서 초기화 됐는지를 비교해서 boolean으로 리턴을 해줌

//            Log.d("aaaa","BakcPressed: a"+nowFragment.getClass().equals(CustomerMainFragment.class)+"");
//            Log.d("bbbb", "BackPressed: b"+CustomerMainFragment.class.isInstance(nowFragment.getClass())
//            if (nowFragment.getClass().equals(CustomerMainFragment.class)) {
//                String test ="";
//            }else {
//                String test ="";
//            }
//            if(fragIndex == 0) {
//
//            }
//
//            if( CustomerMainFragment.class.isInstance(nowFragment)) {
//             String test ="";
//            }else {
//                String test= "";
//
//            }


//            if(nowFragment instanceof CustomerMainFragment) {
//                String test = "";
//            }
//            if(btm_nav.getSelectedItemId() == R.id.muenu_cus){
//
//            }
            //바텀네비게이션 == Fragment가 연동이 되어있는 상태에서는 바텀네비게이션의 아이템 선택부분을 바꿔주면 된다.
            if(CustomerMainFragment.class.isInstance(nowFragment)){
                String test="";
                finish();
            }else if(EmployeeFragment.class.isInstance(nowFragment)){
               btm_nav.setSelectedItemId(R.id.muenu_cus);
            }else {
                String test="";
            }//else 부분이 비정상적으로 실행이 안되는 블럭으로 인식이 됨

            Toast.makeText(MainActivity.this, "종료되는 시점(실제 종료 x)", Toast.LENGTH_SHORT).show();
        }
    }




    //프래그먼트 기억해야함 입력받는 형태로 변경
    //backFragment 실행 해야되는 순간이 있음.
    // nowFragment 실행 해야되는 경우도 있음
    public void changefragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

}