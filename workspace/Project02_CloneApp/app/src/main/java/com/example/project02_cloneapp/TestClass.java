package com.example.project02_cloneapp;

import android.content.Context;
import android.widget.Toast;

public class TestClass {
    // 메소드를 하나 만듬. String을 파라메터로 입력받는, 리턴이 없는,
    // 이 메소드의 기능은 토스트창을 보여줌 (입력받은 String을 통해서 )
    
    public void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    
}