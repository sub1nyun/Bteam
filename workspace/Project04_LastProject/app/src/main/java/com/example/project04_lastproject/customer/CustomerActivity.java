package com.example.project04_lastproject.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project04_lastproject.R;
import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.common.CommonMethod;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class CustomerActivity extends AppCompatActivity {

    TextView tv_info;
    RadioButton rdo_man, rdo_woman;
    EditText edt_email, edt_phone;
    Button btn_close, btn_add;
    Intent intent;
    CustomerVO vo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        intent = getIntent();
         vo = (CustomerVO) intent.getSerializableExtra("vo");
        Toast.makeText(CustomerActivity.this, vo.getName() + ": 이름", Toast.LENGTH_SHORT).show();

        tv_info = findViewById(R.id.tv_info);
        rdo_man = findViewById(R.id.rdo_man);
        rdo_woman = findViewById(R.id.rdo_woman);
        edt_email = findViewById(R.id.edt_email);
        edt_phone = findViewById(R.id.edt_phone);
        btn_close = findViewById(R.id.btn_close);
        btn_add = findViewById(R.id.btn_add);
        boolean isEnable = intent.getBooleanExtra("enable", false);
        //찾은 위젯에 정보를 넣어보기 (vo이용해서)
        // tv_info <= 선택된이름의 고객정보

        tv_info.setText(vo.getName() + "의 정보");
        //성별은 vo가 알고있음
        if (vo.getGender().equals("남")) {
            rdo_man.setChecked(true);
        } else {
            rdo_woman.setChecked(true);
        }

        rdo_man.setEnabled(isEnable);
        rdo_woman.setEnabled(isEnable);
        edt_email.setEnabled(isEnable);
        edt_phone.setEnabled(isEnable);

        rdo_man.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rdo_woman.setChecked(false);
                vo.setGender("남");}
            }
        });
        rdo_woman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rdo_man.setChecked(false);
                    vo.setGender("여");
                }
            }
        });
        edt_email.setText(vo.getEmail() + "");
        edt_phone.setText(vo.getPhone() + "");

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //edt에 내용을 정확히 입력했는지 체크(생각)
                vo.setEmail(edt_email.getText()+"");
                vo.setPhone(edt_phone.getText()+"");
                Gson gson = new Gson();
                Toast.makeText(CustomerActivity.this, gson.toJson(vo), Toast.LENGTH_SHORT).show();
                //String data = gson.toJson(vo);
                AskTask task = new AskTask("update.cu");
                task.addParam("vo", gson.toJson(vo)); //Spring에 갈때 파라메터를 추가해서 가기
                CommonMethod.exuteGet(task);

            }
        });





    }
    public void delete(String id) {
        AskTask task = new AskTask("delete.cu");
        task.addParam("id", id);
        CommonMethod.exuteGet(task);
    }
}