package com.example.testproject02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LastFragment extends Fragment {
    EditText last_tv1, last_tv2;
    Button last_btn;
    Intent intent;
    Activity activity;

    public LastFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_last, container, false);
        last_tv1 = rootView.findViewById(R.id.last_tv1);
        last_tv2 = rootView.findViewById(R.id.last_tv2);
        last_btn = rootView.findViewById(R.id.last_btn);

        last_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tv1 = last_tv1.getText().toString();
                String tv2 = last_tv2.getText().toString();
                if( tv1.length() ==0 || tv2.length() ==0) {
                    Toast.makeText(activity, "값을 입력하세요", Toast.LENGTH_SHORT).show();
                }else {
                    LastDTO dto = new LastDTO(last_tv1.getText().toString(), last_tv2.getText().toString());
                    intent = new Intent(activity, NewActivity.class );
                    intent.putExtra("str1",last_tv1.getText()+"");
                    intent.putExtra("str2",last_tv2.getText()+"");
                    intent.putExtra("dto", dto);
                    startActivity(intent);
                }






            }
        });




        return rootView;
    }
}