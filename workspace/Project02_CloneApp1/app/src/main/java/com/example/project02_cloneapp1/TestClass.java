package com.example.project02_cloneapp1;

import android.content.Context;
import android.widget.Toast;

public class TestClass {

    //메소드를 하나 만듬 String을 파라메터로(그외에는 어떤것을 입력받아도 됨)
    // 입력받는 리턴 X
    //이메소드의 기능은 토스트창을 보여줌 (입력받은 String을 통해서)
    public void toastShow(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
