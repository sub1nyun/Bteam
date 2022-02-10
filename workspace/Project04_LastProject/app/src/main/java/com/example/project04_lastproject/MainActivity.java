package com.example.project04_lastproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.project04_lastproject.common.AskTask;
import com.example.project04_lastproject.customer.CustomerMainFragment;
import com.example.project04_lastproject.employees.EmployeeFragment;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //네트워크 오류 잡기
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        getSupportFragmentManager().beginTransaction().replace(R.id.container, new EmployeeFragment()).commit();
        //editText = findViewById(R.id.edt_mapping);
        //tv1 = findViewById(R.id.tv1);

               // AskTask task = new AskTask(editText.getText()+".te");
              //  task.execute();
                //그냥 생성해서 실행하면 됨

    }
}