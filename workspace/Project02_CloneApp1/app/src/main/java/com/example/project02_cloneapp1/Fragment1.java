package com.example.project02_cloneapp1;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    Context context; //90% 이상 오류가 안 남 (제일 안전한 방법)

    //alt + insert
    public Fragment1(Context context) {
        this.context = context;
    }

    //Fragment는 Componet가 아님
    //Fragment를 만들게 되면 불피요한 코드가 매우 많이 존재한다. final 상태로 인자 값을 한 번만 입력받게 하는
    //변수, OnCreate()메소드 등등,
    //실제로 제일 중요하고 필요한 코드는 onCreateView이다
    //Ac랑 차이는 onCreate <- Activity ( Context를 가진)
    //onCreateView <- Fragment, View객체 (Context를 가지지 않은, 화면에 혼자 떠있을 수 없는 )
    //=> inflater.inflate 화면을 붙이는 행위가 onCreateView에 있기 때문. (find)를 생각 ->연결전엔 사용 불가
    //Fragment도 commit을 해줘야함 (트랜젝션)
    //getFragmentManger() (매니저 참조하기)로 시작 - Context
    //-> beginTransaction() 트랜젝션 시작 (이때 오류가 많음 -> commit() 해야함)
    TextView frag1_tv;
    Button btn1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //위젯(뷰)를 묶어 놓은 그룹 -> 객체로 하나로 만듬 xml에 있는것을 DTO처럼 만듬
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_1, container, false);
        //fragment_1 이라는 xml에 있는 모든 위젯들이 묶여서 ViewGroup에 담김
        //frag1_tv가 묶여 있는 곳에서 찾기
        //context를 받아오는 방식 -> 광범위해서 오류가 없는 방향을 택함

        frag1_tv = rootView.findViewById(R.id.frag1_tv);
        //종료가 될 확률이 더 높음 ▼
        Toast.makeText(frag1_tv.getContext(), "여기뜸", Toast.LENGTH_SHORT).show();

        //fragment1<- 버튼을 추가하고 온클릭리스너 통해서 frag1_tv에 있는 글씨를 버튼클릭됨으로 변경
        btn1 = rootView.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag1_tv.setText("버튼클릭됨");
                //오류날 확률이 높음 ▼
                //화면이 액티비티에 붙고나서 상위 화면에 있는 context를 받아오는 메소드
                //화면이 붙기전에 사용을 하면 어플이 강제 종료가됨. -> 렌더링보다 코드 실행이 빠르면 꺼짐
                Toast.makeText(frag1_tv.getContext(), "여기뜸1", Toast.LENGTH_SHORT).show();
                Toast.makeText(rootView.getContext(), "여기뜸2", Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "여기뜸200", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), "여기뜸3", Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), "여기뜸4", Toast.LENGTH_SHORT).show();

                //메모리 때문에 안드로이드 OS에서 제한을둔것
                TestClass testClass = new TestClass();
                testClass.toastShow(rootView.getContext(), "메세지1");
                testClass.toastShow(context, "메세지2");
                testClass.toastShow(getContext(), "메세지3");
                testClass.toastShow(getActivity(), "메세지4");
            }
        });

        frag1_tv.setText("글씨를 바꾸기");
        //디자인 붙이는 곳 ▼
        return rootView;
    }

}